#**HIDRA**

[proyecto en github](https://github.com/carlokg/com.car_pa_ra.hidra.git)


* **Splash** Ventana de carga de la aplicación, cuenta con una animación de fondo de la galaxia 
y tiene el nombre de la aplicación sobre la animación.

    * **Login** Ventana principal para logearse, desde aquí se puede iniciar sesión o pasar a la
     pantalla de registro me diante dos botones. Recordará el último usuario logeado.
     
    * **Registro** Ventana con campos editables para introducir los datos de la cuenta de usuario, 
        al aceptar se creará un usuario en la base de datos Firebase. Y se accederá a la vista
        principal (MainActivity), dónde se cargarán las diferentes vistas.
        * **MainActivity** Solo contiene el appBar, el cual estará presente en todos los
        fragmentos de la aplicación y un FrameLayout sobre el que se cargarán dichos fragmentos
        (ExploraFragment, SocialFragment, AyudaFragment y PerfilFragment).
        También tiene un Botón flotante para crear nuevos grupos.
            *  **ExploraFragment** Fragmento que carga sobre el MainActivity al pulsar el botón
            correspondiente. El cual contiene un RecyclerView donde se cargarán 
            automáticamente todas las CardView de los grupos, tanto de 'Social' como de 'Ayuda'. 
            *  **SocialFragment** Fragmento que carga sobre el MainActivity al pulsar el botón
            correspondiente. Contendrá lo mismo que ExploraFragment, pero filtrará solamente
            las tarjetas de los grupos clasificados como 'Social'.
            *  **AyudaFragment**  Fragmento que carga sobre el MainActivity al pulsar el botón
            correspondiente. Contendrá lo mismo que ExploraFragment, pero filtrará solamente
            las tarjetas de los grupos clasificados como 'Ayuda'.
            *  **CrearGrupoActivity**  Activity que se carga al pulsar sobre el botón flotante
            del MainActivity, en el se introducen los datos e imagen de un grupo nuevo y
            al pulsar sobre el botón crear, se crea un nuevo grupo en la base de datos de Firebase.
            *  **PerfilFragment** Contendrá la imagen del usuario y su foto, todos los datos
            se recogen de la base de datos Firebase. Si se pulsa sobre la ubicación, se abrirá
            MapaAcivity.
                *  **MapaActivity** Activity en el cual se carga el mapa, con la ubiccación
                de la ciudad del usuario.
            *  **Ajustes** Al pulsar sobre el botón de ajustes, se abrirá una ventana emergente
            con diferentes opciones de ajustes e información.
                *  **LogOut** Si se pulsa sobre este elemento de la ventana emergente, se
                cerrará la sesión en Firebase, y llevará a LoginActivity para que se vuelva a 
                iniciar sesión o crear cuenta.
                *  **AboutFragment** Si se pulsa sobre este elemento de la ventana emergente,
                se cargará un fragmento con información de los desarrolladores.
                    