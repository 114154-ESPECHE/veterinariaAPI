$(document).ready(function() {
    $('#buscarMascotaForm').submit(function(event) {
        event.preventDefault();

        var nombre = $('#nombreMascotaInput').val();

        $.ajax({
            url: 'http://localhost:8080/mascotas/mascota-ResponseDTO/' + nombre,
            type: 'GET',
            success: function(response) {
                console.log(response);

                $('#nombreMascota').text(response.nombre);
                $('#colorMascota').text(response.color);
                $('#edadMascota').text(response.edad);
                $('#especieMascota').text(response.especie);
                $('#nombreCliente').text(response.nombreCliente);
                $('#apellidoCliente').text(response.apellidoCliente);
            },
            error: function(error) {
                console.error('Error al obtener la mascota:', error);
            }
        });
    });

    
    $('.carousel').carousel();
    
});
