
<%@ page import="uk.org.commedia.epg.Station" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'station.label', default: 'Station')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'station.id.label', default: 'Id')}" />
                            <g:sortableColumn property="name" title="${message(code: 'station.name.label', default: 'Name')}" />
                            <g:sortableColumn property="url" title="${message(code: 'station.url.label', default: 'URL')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${stationInstanceList}" status="i" var="stationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${stationInstance.id}">${fieldValue(bean: stationInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: stationInstance, field: "name")}</td>
                            <td>${fieldValue(bean: stationInstance, field: "url")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${stationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
