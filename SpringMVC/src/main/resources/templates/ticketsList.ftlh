<#import "parts/common.ftlh" as c>

<@c.page>
    List of tickets

    <table>
        <thead>
        <tr>
            <th>Username</th>
            <th>Event name</th>
            <th>price</th>
            <th>Creation date</th>
        </tr>
        </thead>
        <tbody>
        <#list page.content as ticket>
            <tr>
                <td>${ticket.user.username}</td>
                <td>${ticket.event.name}</td>
                <td>${ticket.event.price}</td>
                <td>${ticket.creationDate}</td>
            </tr>
        </#list>
        </tbody>
    </table>

    <a href="/ticket/my/downloadPdf">Download this</a>
</@c.page>