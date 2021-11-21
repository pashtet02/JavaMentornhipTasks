<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post" action="/events/create">
            <input type="text" name="name" placeholder="Enter event name" />
            <input type="text" name="details" placeholder="Event details">
            <input type="number" name="price" placeholder="Event price">
            <input type="text" name="location" placeholder="Event location">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    </div>
    <div>Events list</div>
    <#list events as event>
        <div>
            <b>${event.id}</b>
            <span>${event.name}</span>
            <i>${event.details}</i>
            <b>${event.price}</b>
            <b>${event.location}</b>
            <a href="/ticket?eventId=${event.id}">Order</a>
        </div>
    <#else>
        No events
    </#list>

    <hr>
    <div>
        <form method="post" action="/user/deposit">
            <input type="number" name="deposit" placeholder="Event amount of money">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Donate</button>
        </form>
    </div>

</@c.page>