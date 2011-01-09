package uk.org.commedia.epg

import grails.converters.JSON
import grails.converters.XML
import grails.plugins.springsecurity.Secured

class StationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

 
   def list = {
        println "List Stations - ${request.format}."
        def response = null;
        if(request?.format && request.format != "html"){
          // response = [stationInstanceList: Station.list(), stationInstanceTotal: Station.count()]
          // println "Processing non html response"
          def live_stations = Station.findAllByLive(true);
          response = [stationInstanceList: live_stations, stationInstanceTotal:live_stations.size(), flooble: "hello"]
        }
        else {
          params.max = Math.min(params.max ? params.int('max') : 10, 100)
          response = [stationInstanceList: Station.list(params), stationInstanceTotal: Station.count()]
        }

        withFormat {
           html response
            xml { render response as XML }
            json { render response as JSON }
        }

    }

    def create = {
        def stationInstance = new Station()
        stationInstance.properties = params
        return [stationInstance: stationInstance]
    }

    def save = {
        def stationInstance = new Station(params)
        if (stationInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'station.label', default: 'Station'), stationInstance.id])}"
            redirect(action: "show", id: stationInstance.id)
        }
        else {
            render(view: "create", model: [stationInstance: stationInstance])
        }
    }

    def show = {
        def stationInstance = Station.get(params.id)
        if (!stationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'station.label', default: 'Station'), params.id])}"
            redirect(action: "list")
        }
        else {
            [stationInstance: stationInstance]
        }
    }

    def edit = {
        def stationInstance = Station.get(params.id)
        if (!stationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'station.label', default: 'Station'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [stationInstance: stationInstance]
        }
    }

    def update = {
        def stationInstance = Station.get(params.id)
        if (stationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (stationInstance.version > version) {
                    
                    stationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'station.label', default: 'Station')] as Object[], "Another user has updated this Station while you were editing")
                    render(view: "edit", model: [stationInstance: stationInstance])
                    return
                }
            }
            stationInstance.properties = params
            if (!stationInstance.hasErrors() && stationInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'station.label', default: 'Station'), stationInstance.id])}"
                redirect(action: "show", id: stationInstance.id)
            }
            else {
                render(view: "edit", model: [stationInstance: stationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'station.label', default: 'Station'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def stationInstance = Station.get(params.id)
        if (stationInstance) {
            try {
                stationInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'station.label', default: 'Station'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'station.label', default: 'Station'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'station.label', default: 'Station'), params.id])}"
            redirect(action: "list")
        }
    }
}
