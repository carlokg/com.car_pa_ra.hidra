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
             fragmentos de la aplicación y un recyclerView sobre el que se cargarán dichos fragmentos
             (ExploraFragment, SocialFragment, AyudaFragment y PerfilFragment)
        
                 *  **Explora Fragment** aaaaaaaaaa
                 *  **SocialFragment** aaaaaaaaaa
                 *  **AyudaFragment** aaaaaaaaaa
                 *  **PerfilFragment** aaaaaaaaaa