// Function to update the style based on the selected radio button
const updateSelectedStar = () => {
    // Remove the 'checked-star' class from all <i> elements
    const allStars = document.querySelectorAll('.stars-radio i');
    allStars.forEach(star => star.classList.remove('checked-star'));

    // Find the checked radio input within .stars-radio
    const checkedInput = document.querySelector('.stars-radio input[type=radio]:checked');
    
    // If a checked input is found, add a class to the preceding <i> element
    if (checkedInput) {
        let checkedLabel = checkedInput.parentElement;

        while (checkedLabel) {
            checkedLabel.querySelector('i').classList.add('checked-star');
            checkedLabel = checkedLabel.previousElementSibling;
        }
    }
};

window.addEventListener('load', () => {
    // Initially set the style
    updateSelectedStar();

    // Add event listeners to each radio button to update the style on change
    let radioButtons = document.querySelectorAll('.stars-radio input[type=radio]');
    radioButtons.forEach(function(radio) {
        radio.addEventListener('change', updateSelectedStar);
    });
});
