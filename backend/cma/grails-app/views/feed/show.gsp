
<%@ page import="uk.org.commedia.epg.Feed" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feed.label', default: 'Feed')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.playerFeedCode.label" default="Player Feed Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "playerFeedCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.description.label" default="Description" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "description")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.title.label" default="Title" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "title")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.copyright.label" default="Copyright" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "copyright")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.skipHours.label" default="Skip Hours" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "skipHours")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.skipDays.label" default="Skip Days" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "skipDays")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.baseUrl.label" default="Base Url" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "baseUrl")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.items.label" default="Items" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${feedInstance.items}" var="i">
                                    <li><g:link controller="feedItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="feed.playerFeedName.label" default="Player Feed Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: feedInstance, field: "playerFeedName")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${feedInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
