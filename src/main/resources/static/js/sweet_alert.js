
export default function swalSuccess(message){
    swal.fire({
        position: 'center',
        icon: 'success',
        title: message,
        showConfirmButton: false,
        timer: 2500
    })
}