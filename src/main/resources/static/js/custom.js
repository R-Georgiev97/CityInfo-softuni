import {ajaxDelete, ajaxGet, ajaxPost} from "./ajax.js";

$(document).ready(function () {
    //ADMIN CATEGORIES
    //add category field
    $('.js-add-field').click(function () {
        let fieldsHolder = $('.js-fields-holder-disabled ').clone();
        fieldsHolder.removeClass("d-none js-fields-holder-disabled").addClass("d-flex")
        fieldsHolder.find('.form-control').each((index, field) => {
            $(field).removeAttr('disabled')
        })
        $('.fields-container').append(fieldsHolder)
    })

    //remove category field
    $(document).on('click', '.js-remove-field', function () {
        let fieldId = $(this).data('category_field_id')
        if (fieldId) {
            confirmAndDelete("/admin/category-fields/" + fieldId)
            $(this).parents("tr").remove()

        }
        $(this).parents('.js-fields-holder').remove()
    })

    //END ADMIN CATEGORIES

    //ADMIN USERS

    $('.js-users-del').on('click', function () {
        let userId = $(this).data('user_id')

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
                ajaxPost("/admin/users/" + userId + "/delete", {})
                $(this).parents("tr").remove()
            }
        })
    })

    //END ADMIN USERS

    //ADMIN CITIEs

    $('.js-city_del').on('click', function () {
        let cityId = $(this).data('city_id')
        confirmAndDelete("/admin/cities/" + cityId)
        $(this).parents("tr").remove()

    })

    //END CITIES

    //OBJECTS
    $('.js-object-category').on('change', function () {
        let categorySlug = $(this).find('option:selected').val();
        let fieldsContainer = $('.js-object-fields');
        fieldsContainer.find('.js-object-field').remove();

        ajaxGet('/api/category-fields?category_slug=' + categorySlug, function (response) {
            $.each(response, function (i, field) {
                let fieldHolder = getCategoryFieldInput(field)
                fieldsContainer.append(fieldHolder)
            })
        })
        $(this).find('.js-not-selected-option').attr('disabled','disabled');
    })
        //END OBJECTS

        //GENERAL
        $('.js-admin-index-delete-entity-form').on('submit', function (e) {
            e.preventDefault()
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
                    $(this).unbind('submit').submit()
                }
            })
        })


    });

    function confirmAndDelete(url) {
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
                ajaxDelete(url)
            }
        })
    }

    function getCategoryFieldInput(field) {
        let fieldHolder = $('.js-object-field-hidden').clone()
        fieldHolder.removeClass('js-object-field-hidden d-none').addClass('js-object-field')
        let label = fieldHolder.find('label')
        label.attr('for', field.slug)
        label.text(field.name)
        let inputEl = fieldHolder.find('input')
        inputEl.removeAttr('disabled')
            .attr('name', field.slug)
            .attr('id',field.slug)
            .attr('placeholder',field.name)

        return fieldHolder;
    }


