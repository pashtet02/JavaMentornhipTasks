<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Add new user
    ${message!}

    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label> Add money: <input type="number" name="deposit"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" min="0"/>
        <div><input type="submit" value="Sign In"/></div>
    </form>
</@c.page>