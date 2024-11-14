let state = false;

function registrationFormChange() {
    let mainForm = document.getElementById("mainPart");
    let subForm = document.getElementById("subPart");

    let mainBt = document.getElementById("mainBt");
    let subBt = document.getElementById("subBt");

    let mainBtTextSpan = mainBt.querySelector("span");
    let subBtTextSpan = subBt.querySelector("span");

    subBt.disabled = true;

    let mainFormColor = getComputedStyle(mainForm).backgroundColor;
    let subFormColor = getComputedStyle(subForm).backgroundColor;

    mainForm.style.backgroundColor = subFormColor;
    subForm.style.backgroundColor = mainFormColor;

    mainBtTextSpan.classList.add("fade-out");
    subBtTextSpan.classList.add("fade-out");

    setTimeout(() => {
        let mainBtText = mainBtTextSpan.textContent;
        let subBtText = subBtTextSpan.textContent;

        mainBtTextSpan.textContent = subBtText;
        subBtTextSpan.textContent = mainBtText;

        mainBtTextSpan.classList.remove("fade-out");
        mainBtTextSpan.classList.add("fade-in");

        subBtTextSpan.classList.remove("fade-out");
        subBtTextSpan.classList.add("fade-in");

        setTimeout(() => {
            mainBtTextSpan.classList.remove("fade-in");
            subBtTextSpan.classList.remove("fade-in");
            subBt.disabled = false;
        }, 500);
    }, 500);

    state = !state;

    document.querySelectorAll('input').forEach(item => {
        state ? item.classList.add("regFormActive") : item.classList.remove("regFormActive");
    });

    state ? document.getElementsByClassName("header").item(0).classList.add("regFormActive")
        : document.getElementsByClassName("header").item(0).classList.remove("regFormActive");
}
