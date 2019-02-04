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
    
    static message( severity, title, message ){
        if(message && message.constructor === Array){
            message.forEach(msg => toastr[severity]( msg, title ))
        }else{
            toastr[severity]( message, title )
        }
    }

    static error = ( message ) => {
        Messages.message('error', 'Erro', message)
    }

    static info( message ){
        Messages.message('info', 'Informação', message)
    }

    static warning( message ){
        Messages.message('warning', 'Aviso', message)
    }

    static success( message ){
        Messages.message('success', 'Sucesso', message)
    }
    
}