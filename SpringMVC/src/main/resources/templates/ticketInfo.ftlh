<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <a href="/events">To the main page</a>
    </div>
    <div>Ticket info</div>
        <div>
            <span>Event name: ${ticket.event.name}</span><br>
            <span>Event details: ${ticket.event.details}</span><br>
            <b>Price:  ${ticket.event.price} $</b><br>
            <b>Event date: ${ticket.creationDate!}</b>
            <form method="post" action="/ticket/purchase?eventId=${ticket.event.id}">
                <input type="submit" value="Buy">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </form>
        </div>
</@c.page>