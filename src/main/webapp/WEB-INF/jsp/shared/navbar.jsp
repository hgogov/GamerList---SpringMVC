<div class="page-wrapper chiller-theme toggle">
    <nav class="navbar navbar-expand-md navbar-light navbar-laravel">
        <div class="container">
            <a class="navbar-brand" href="/games">
                Gamer List
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <!-- Left Side Of Navbar -->
                <ul class="navbar-nav mr-auto ac">
                    <li class="nav-item active">
                        <a class="nav-link" href="/games">Home <span class="sr-only">(current)</span></a>
                    </li>
                </ul>

                <ul class="nav navbar-nav mr-auto">
                </ul>

                <!-- Right Side Of Navbar -->
                <ul class="navbar-nav ml-auto">
                    <!-- Authentication Links -->
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="{{ route('register') }}">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout" onclick="
                                                     document.getElementById('logout-form').submit();">
                            Logout
                        </a>

                        <form id="logout-form" action="/logout" method="POST" style="display: none;">
                        </form>
                    </li>

                    <!-- <li class="nav-item dropdown">
                        <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                            ${user} <span class="caret"></span>
                        </a> -->

                    <!-- <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown"> -->
                    <!-- @if(auth()->user()->isAdmin())<a href="/dashboard" class="dropdown-item">Dashboard</a>@endif -->
                    <!-- <a class="dropdown-item" href="/logout" onclick="
                                                     document.getElementById('logout-form').submit();">
                            Logout
                        </a>

                        <form id="logout-form" action="/logout" method="POST" style="display: none;">
                        </form> -->
                    <!-- </div> -->
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>