import {ajaxDelete} from "./ajax.js";


const host = "http://localhost:8080"

$(document).ready(function () {
    //ADMIN CATEGORIES
    //add category field
    $('.js-add-field').click(function () {
        let fieldsHolder = $('.js-fields-holder-disabled ').clone();
        fieldsHolder.removeClass("d-none js-fields-holder-disabled").addClass("d-flex")
        fieldsHolder.find('.form-control').each((index,field) => {
            $(field).removeAttr('disabled')
        })
        $('.fields-container').append(fieldsHolder)
    })

    //remove category field
    $(document).on('click', '.js-remove-field', function () {
        let fieldId = $(this).data('category_field_id')
        if (fieldId) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    ajaxDelete("/admin/category-fields/"+fieldId);
                }
            })
        }
        $(this).parents('.js-fields-holder').remove()
    })

    //END ADMIN CATEGORIES
});
