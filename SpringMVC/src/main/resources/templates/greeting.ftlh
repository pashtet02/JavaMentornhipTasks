<#import "parts/common.ftlh" as c>

<@c.page>
    <div>Hello,
        <#if Session.username??>
            ${Session.username}
            <#else>
            user!
        </#if>
    </div>
    <a href="/events">Events page</a>
    <br>
    <a href="/user">Users list</a>
</@c.page>