<#import "blocks/template.ftlh" as t>
<@t.template user>
    `<h1 style="text-align: center">Функции администратора</h1><hr>
    <table  class="table table-bordered border-dark">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Состояние активности</th>
            <th scope="col">Роли</th>
            <th scope="col">Бан</th>
            <th scope="col">Редактировать роль</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <th><#list user.roles as role>${role}</#list></th>
                <th>
                    <form action="/admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-dark" value="Бан">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${user.id}" style="color:#FCE883">Редактировние</a></th>
                <th><a href="/user/${user.id}" style="color:#FCE883">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Пользователи отсутствуют</h3>
        </#list>
    </table>
</@t.template>