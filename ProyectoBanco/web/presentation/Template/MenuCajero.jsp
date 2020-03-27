<%@page import="banco.logica.Usuario"%>
<% Usuario usuario = (Usuario) session.getAttribute("usuario");%>
<div class="divMenu">
    <br><br>
    <center>
        <img src="/ProyectoBanco/images/menuIcon/world.png"  width="30" height="30" > 
    </center>
    <center>
        Plataforma
        Digital
    </center>
    <br>
    <ul class="mainMenu">
        <li class="item" id="account">
            <a href="#account" class="btn"><i class="fas fa-user-circle"></i> <img src="/ProyectoBanco/images/menuIcon/transFlecha.png"  width="20" height="20" id="icon"> Gestión de <br> movimientos</a>
            <div class="subMenu">
                <form>
                    <a  href="/ProyectoBanco/presentation/login/transferencia" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/transferencia.png"  id="subIcon"> Transferencias</a>
                <a href="/ProyectoBanco/presentation/login/movimientos/show" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/debito.png"  id="subIcon"> Depósitos</a>
                <a href="/ProyectoBanco/presentation/login/movimientos/show" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/debito.png"  id="subIcon"> Rétiros</a>
                <a href="/ProyectoBanco/presentation/login/movimientos/show" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/debito.png"  id="subIcon"> Acreditar Intréses</a>
                <a href="" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/money.png"  id="subIcon"> Mi saldo</a>
                </form>

            </div>
        </li>
        <li class="item" id="about">
            <a href="#about" class="btn" ><i class="fas fa-address-card" ></i><img src="/ProyectoBanco/images/menuIcon/infoPersonal.png"  width="25" height="25" id="icon"> <div id="textoIni">Gestión de cuentas</div></a>
            <div class="subMenu">
                <a href="" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/misCuentas.png"  width="30" height="30" id="subIcon">Editar datos <br> personales</a>
                <a href="/ProyectoBanco/presentation/login/infoPersonal" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/perfil.png"  width="30" height="30" id="subIcon"> Crear una cuenta</a>
                <a href="/ProyectoBanco/presentation/login/infoPersonal" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/perfil.png"  width="30" height="30" id="subIcon"> Crear un cliente</a>
            </div>
        </li>
        <li class="item" id="support">
            <a href="#support" class="btn"><i class="fas fa-info"></i><img src="/ProyectoBanco/images/menuIcon/ayuda.png"  width="25" height="25" id="icon"> Ayuda</a>
            <div class="subMenu">
                <a href="" id="textoSub"><img src="/ProyectoBanco/images/menuIcon/about.png"  width="25" height="25" id="subIcon"> Sobre nosotros</a>
            </div>
        </li>
        <li class="item">
            <a href="#" class="btn"><i class="fas fa-sign-out-alt"></i><img src="/ProyectoBanco/images/menuIcon/salir.png"  width="30" height="30" id="icon" > Cerrar Sesión</a>
        </li>
    </ul>
</div>