<html>
   <head>
        <title><g:layoutTitle default="Community Media Association EPG" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="jquery" />
    </head>
    <body>

      <div id="spinner" class="spinner" style="display:none;">
        <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
      </div>
      <div id="CMALogo"></a></div>

       <div class="nav">
            <div class="auth">
              <sec:ifLoggedIn>
                Welcome Back <sec:loggedInUserInfo field="username"/>
                <span class="menuButton"><g:link controller="logout">Logout</g:link></span>
              </sec:ifLoggedIn>
              <sec:ifNotLoggedIn>
                <span class="menuButton"><g:link controller="login">Login</g:link></span>
              </sec:ifNotLoggedIn>
            </div>

            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>

            <sec:ifLoggedIn>
              <span class="menuButton"><g:link class="list" controller="station" action="index">Stations</g:link></span>
              <span class="menuButton"><g:link class="list" controller="feed" action="index">Feeds</g:link></span>
            </sec:ifLoggedIn>
            <sec:ifAnyGranted roles="admin">
              <span class="menuButton"><g:link class="list" controller="user" action="index">Users</g:link></span>
              <span class="menuButton"><g:link class="list" controller="role" action="index">Roles</g:link></span>
            </sec:ifAnyGranted>
        </div>

      <g:layoutBody />

    </body>
</html>

