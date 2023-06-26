<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <form action="/registration" method="post">
        <div class="form-group">
            <label for="name">Имя пользователя</label>
            <input type="text" class="form-control" required id="name" name="name">
        </div>
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <div class="input-group mb-3">
            <input type="email" class="form-control" id="email" required name="email">
            <div class="input-group-append">
                   <span class="input-group-text">@</span>
                                </div>
                  </div>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Номер телефона</label>
            <div class="input-group mb-3">
            <input type="text" class="form-control" id="phoneNumber" required name="phoneNumber">
            <div class="input-group-append">
                   <span class="input-group-text">☏</span>
               </div>
        </div>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" id="password" required name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>