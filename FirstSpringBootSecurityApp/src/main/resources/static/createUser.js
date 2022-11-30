async function createUser() {
    $('#addUser').click(async () =>  {
        let addUserForm = $('#addForm')
        let username = addUserForm.find('#usernameCreate').val().trim();
        let password = addUserForm.find('#passwordCreate').val().trim();
        let firstName = addUserForm.find('#firstNameCreate').val().trim();
        let lastName = addUserForm.find('#lastNameCreate').val().trim();
        let age = addUserForm.find('#ageCreate').val().trim();
        let email = addUserForm.find('#emailCreate').val().trim();
        let checkedRoles = () => {
            let array = []
            let options = document.querySelector('#rolesCreate').options
            for (let i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    array.push(roleList[i])
                }
            }
            return array;
        }
        let data = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            age: age,
            email: email,
            roles: checkedRoles()
        }
        //вставляем в ранне объявленную константу полученные из формы значения

        await userFetch.addNewUser(data);

    });
}