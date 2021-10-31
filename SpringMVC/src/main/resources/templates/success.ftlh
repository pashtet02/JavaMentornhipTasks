<#import "parts/common.ftlh" as c>

<@c.page>
    <div>Ticket on the event: ${ticket.event.name} bought successfully!</div>
    <p>Thanks for using our service, please keep distance!</p>

    <div>
        <span>Event name: ${ticket.event.name}</span><br>
        <span>Event details: ${ticket.event.details}</span><br>
        <b>Price:  ${ticket.event.price} $</b><br>
        <b>Event date: ${ticket.creationDate!}</b>
    </div>
    <hr>
    <a href="/events">To events page!</a>
</@c.page>