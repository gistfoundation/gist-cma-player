
<%@ page import="uk.org.commedia.epg.Feed" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feed.label', default: 'Feed')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'feed.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="playerFeedCode" title="${message(code: 'feed.playerFeedCode.label', default: 'Player Feed Code')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'feed.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="title" title="${message(code: 'feed.title.label', default: 'Title')}" />
                        
                            <g:sortableColumn property="copyright" title="${message(code: 'feed.copyright.label', default: 'Copyright')}" />
                        
                            <g:sortableColumn property="skipHours" title="${message(code: 'feed.skipHours.label', default: 'Skip Hours')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${feedInstanceList}" status="i" var="feedInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${feedInstance.id}">${fieldValue(bean: feedInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: feedInstance, field: "playerFeedCode")}</td>
                        
                            <td>${fieldValue(bean: feedInstance, field: "description")}</td>
                        
                            <td>${fieldValue(bean: feedInstance, field: "title")}</td>
                        
                            <td>${fieldValue(bean: feedInstance, field: "copyright")}</td>
                        
                            <td>${fieldValue(bean: feedInstance, field: "skipHours")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${feedInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
