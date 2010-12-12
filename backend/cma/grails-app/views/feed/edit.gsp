

<%@ page import="uk.org.commedia.epg.Feed" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feed.label', default: 'Feed')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${feedInstance}">
            <div class="errors">
                <g:renderErrors bean="${feedInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${feedInstance?.id}" />
                <g:hiddenField name="version" value="${feedInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="playerFeedCode"><g:message code="feed.playerFeedCode.label" default="Player Feed Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'playerFeedCode', 'errors')}">
                                    <g:textField name="playerFeedCode" value="${feedInstance?.playerFeedCode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="feed.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${feedInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="title"><g:message code="feed.title.label" default="Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" value="${feedInstance?.title}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="copyright"><g:message code="feed.copyright.label" default="Copyright" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'copyright', 'errors')}">
                                    <g:textField name="copyright" value="${feedInstance?.copyright}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="skipHours"><g:message code="feed.skipHours.label" default="Skip Hours" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'skipHours', 'errors')}">
                                    <g:textField name="skipHours" value="${feedInstance?.skipHours}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="skipDays"><g:message code="feed.skipDays.label" default="Skip Days" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'skipDays', 'errors')}">
                                    <g:textField name="skipDays" value="${feedInstance?.skipDays}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="baseUrl"><g:message code="feed.baseUrl.label" default="Base Url" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'baseUrl', 'errors')}">
                                    <g:textField name="baseUrl" value="${feedInstance?.baseUrl}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="items"><g:message code="feed.items.label" default="Items" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'items', 'errors')}">
                                    
<ul>
<g:each in="${feedInstance?.items?}" var="i">
    <li><g:link controller="feedItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="feedItem" action="create" params="['feed.id': feedInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'feedItem.label', default: 'FeedItem')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="playerFeedName"><g:message code="feed.playerFeedName.label" default="Player Feed Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedInstance, field: 'playerFeedName', 'errors')}">
                                    <g:textField name="playerFeedName" value="${feedInstance?.playerFeedName}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
