import toastr from 'toastr'

toastr.options = {
    "closeButton": true,
    "debug": false,
    "newestOnTop": true,
    "progressBar": true,
    "positionClass": "toast-top-right",
    "preventDuplicates": true,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

export default class Messages {
    
    static message(severity, title, message){
        toastr[severity]( message, title )
    }

    static error = ( message ) => {
        Messages.message('error', 'Ocorreu um erro', message)
    }

    static info( message ){
        Messages.message('info', 'Ocorreu um erro', message)
    }

    static warning( message ){
        Messages.message('warning', 'Ocorreu um erro', message)
    }

    static success( message ){
        Messages.message('success', 'Ocorreu um erro', message)
    }
    
}