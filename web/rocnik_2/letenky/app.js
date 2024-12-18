let passengerDiv;
const maxPassengers = 6;
const countries = [
    { country: "Česká republika", value: "prg", name: "Praha (PRG)" },
    { country: "Česká republika", value: "brq", name: "Brno (BRQ)" },
    { country: "Česká republika", value: "osr", name: "Ostrava (OSR)" },
    { country: "USA", value: "jfk", name: "New York (JFK)" },
    { country: "USA", value: "lax", name: "Los Angeles (LAX)" },
    { country: "USA", value: "ord", name: "Chicago (ORD)" },
    { country: "USA", value: "dfw", name: "Dallas/Fort Worth (DFW)" },
    { country: "USA", value: "den", name: "Denver (DEN)" },
    { country: "USA", value: "atl", name: "Atlanta (ATL)" },
    { country: "USA", value: "sfo", name: "San Francisco (SFO)" },
    { country: "USA", value: "mia", name: "Miami (MIA)" },
    { country: "USA", value: "las", name: "Las Vegas (LAS)" },
    { country: "USA", value: "sea", name: "Seattle (SEA)" },
    { country: "Velká Británie", value: "lhr", name: "Londýn (LHR)" },
    { country: "Velká Británie", value: "lgw", name: "Londýn (LGW)" },
    {
        country: "Velká Británie",
        value: "man",
        name: "Manchester (MAN)",
    },
    {
        country: "Velká Británie",
        value: "edi",
        name: "Edinburgh (EDI)",
    },
    { country: "Německo", value: "fra", name: "Frankfurt (FRA)" },
    { country: "Německo", value: "muc", name: "Mnichov (MUC)" },
    { country: "Německo", value: "txl", name: "Berlín (TXL)" },
    { country: "Německo", value: "ham", name: "Hamburg (HAM)" },
    { country: "Francie", value: "cdg", name: "Paříž (CDG)" },
    { country: "Francie", value: "ory", name: "Paříž (ORY)" },
    { country: "Francie", value: "nce", name: "Nice (NCE)" },
    { country: "Francie", value: "lys", name: "Lyon (LYS)" },
    { country: "Japonsko", value: "nrt", name: "Tokio (NRT)" },
    { country: "Japonsko", value: "hnd", name: "Tokio (HND)" },
    { country: "Japonsko", value: "kix", name: "Osaka (KIX)" },
    { country: "Japonsko", value: "ngo", name: "Nagoya (NGO)" },
    { country: "Čína", value: "pek", name: "Peking (PEK)" },
    { country: "Čína", value: "pvg", name: "Šanghaj (PVG)" },
    { country: "Čína", value: "can", name: "Guangzhou (CAN)" },
    { country: "Čína", value: "hkg", name: "Hongkong (HKG)" },
    { country: "Austrálie", value: "syd", name: "Sydney (SYD)" },
    { country: "Austrálie", value: "mel", name: "Melbourne (MEL)" },
    { country: "Austrálie", value: "bne", name: "Brisbane (BNE)" },
    { country: "Austrálie", value: "per", name: "Perth (PER)" },
    { country: "Kanada", value: "yyz", name: "Toronto (YYZ)" },
    { country: "Kanada", value: "yvr", name: "Vancouver (YVR)" },
    { country: "Kanada", value: "yyc", name: "Calgary (YYC)" },
    { country: "Kanada", value: "yul", name: "Montreal (YUL)" },
    { country: "Brazílie", value: "gru", name: "São Paulo (GRU)" },
    { country: "Brazílie", value: "gig", name: "Rio de Janeiro (GIG)" },
    { country: "Brazílie", value: "bsb", name: "Brasília (BSB)" },
    { country: "Brazílie", value: "cgh", name: "São Paulo (CGH)" },
    { country: "Indie", value: "del", name: "Dillí (DEL)" },
    { country: "Indie", value: "bom", name: "Mumbai (BOM)" },
    { country: "Indie", value: "maa", name: "Chennai (MAA)" },
    { country: "Indie", value: "blr", name: "Bengaluru (BLR)" },
    { country: "Mexiko", value: "mex", name: "Mexico City (MEX)" },
    { country: "Mexiko", value: "cun", name: "Cancún (CUN)" },
    { country: "Mexiko", value: "gdl", name: "Guadalajara (GDL)" },
    { country: "Mexiko", value: "mtx", name: "Monterrey (MTX)" },
    { country: "Rusko", value: "svo", name: "Moskva (SVO)" },
    { country: "Rusko", value: "dme", name: "Moskva (DME)" },
    { country: "Rusko", value: "led", name: "Petrohrad (LED)" },
    { country: "Rusko", value: "ekb", name: "Jekatěrinburg (SVX)" },
    { country: "Itálie", value: "fco", name: "Řím (FCO)" },
    { country: "Itálie", value: "mxp", name: "Milán (MXP)" },
    { country: "Itálie", value: "vce", name: "Benátky (VCE)" },
    { country: "Itálie", value: "nap", name: "Neapol (NAP)" },
    { country: "Španělsko", value: "mad", name: "Madrid (MAD)" },
    { country: "Španělsko", value: "bcn", name: "Barcelona (BCN)" },
    { country: "Španělsko", value: "agp", name: "Málaga (AGP)" },
    {
        country: "Španělsko",
        value: "pmi",
        name: "Palma de Mallorca (PMI)",
    },
    { country: "Turecko", value: "ist", name: "Istanbul (IST)" },
    { country: "Turecko", value: "saw", name: "Istanbul (SAW)" },
    { country: "Turecko", value: "ayt", name: "Antalya (AYT)" },
    { country: "Turecko", value: "esb", name: "Ankara (ESB)" },
    {
        country: "Spojené arabské emiráty",
        value: "dxb",
        name: "Dubaj (DXB)",
    },
    {
        country: "Spojené arabské emiráty",
        value: "auh",
        name: "Abú Dhabí (AUH)",
    },
    {
        country: "Spojené arabské emiráty",
        value: "shj",
        name: "Sharjah (SHJ)",
    },
    { country: "Katar", value: "doh", name: "Dauhá (DOH)" },
    { country: "Saúdská Arábie", value: "ruh", name: "Rijád (RUH)" },
    { country: "Saúdská Arábie", value: "jed", name: "Džidda (JED)" },
    { country: "Saúdská Arábie", value: "dmm", name: "Dammám (DMM)" },
    { country: "Jižní Korea", value: "icn", name: "Soul (ICN)" },
    { country: "Jižní Korea", value: "pvg", name: "Busan (PUS)" },
    { country: "Thajsko", value: "bkk", name: "Bangkok (BKK)" },
    { country: "Thajsko", value: "hkt", name: "Phuket (HKT)" },
    { country: "Singapur", value: "sin", name: "Singapur (SIN)" },
    { country: "Malajsie", value: "kul", name: "Kuala Lumpur (KUL)" },
    { country: "Indonésie", value: "cgk", name: "Jakarta (CGK)" },
    { country: "Indonésie", value: "dps", name: "Bali (DPS)" },
    { country: "Filipíny", value: "mnl", name: "Manila (MNL)" },
    {
        country: "Vietnam",
        value: "sgn",
        name: "Ho Či Minovo Město (SGN)",
    },
    { country: "Vietnam", value: "han", name: "Hanoj (HAN)" },
    { country: "Egypt", value: "cai", name: "Káhira (CAI)" },
    { country: "Egypt", value: "hrg", name: "Hurghada (HRG)" },
    {
        country: "Jižní Afrika",
        value: "jnb",
        name: "Johannesburg (JNB)",
    },
    {
        country: "Jižní Afrika",
        value: "cpt",
        name: "Kapské Město (CPT)",
    },
    { country: "Nigérie", value: "los", name: "Lagos (LOS)" },
    { country: "Keňa", value: "nbu", name: "Nairobi (NBO)" },
];

function updateInputAttributes(newId, element) {
    document
        .querySelector(`#passenger${newId} .${element}`)
        .setAttribute("id", `${element}${newId}`);
    document
        .querySelector(`#passenger${newId} .${element}`)
        .setAttribute("name", `${element}${newId}`);
    document
        .querySelector(`label[for="${element}"]`)
        .setAttribute("for", `${element}${newId}`);
}

function addPassenger(id) {
    const newId = id + 1;
    const addBtn = document.querySelector(`#passenger${id} + #addBtn`);
    addBtn.remove();
    addBtn.setAttribute("href", `javascript:addPassenger(${newId})`);

    if (id > 1)
        document.querySelector(`#passenger${id} .title .closeBtn`).remove();
    const closeBtn = document.createElement("a");
    closeBtn.setAttribute("class", "closeBtn");
    closeBtn.setAttribute("href", `javascript:removePassenger(${newId})`);
    closeBtn.textContent = "Odebrat";

    const clone = passengerDiv.cloneNode(true);
    clone.id = `passenger${newId}`;

    const passengerForm = document.getElementById("passengers");
    passengerForm.appendChild(clone);
    if (newId != maxPassengers) passengerForm.appendChild(addBtn);
    document.querySelector(`#passenger${newId} .title`).appendChild(closeBtn);

    document.querySelector(
        `#passenger${newId} .title h4`
    ).textContent = `${newId}. cestující`;

    const passengerInfo = [
        "gender",
        "firstname",
        "surname",
        "birth",
        "phone",
        "email",
    ];

    for (let i = 0; i < passengerInfo.length; i++) {
        updateInputAttributes(newId, passengerInfo[i]);
        addInputListeners(
            document.querySelector(`#${passengerInfo[i]}${newId}`)
        );
    }

    addDateInputListeners(document.querySelector(`#passenger${newId} .birth`));
}

function removePassenger(id) {
    const oldId = id - 1;

    if (id != maxPassengers) {
        document
            .querySelector(`#passenger${id} + #addBtn`)
            .setAttribute("href", `javascript:addPassenger(${oldId})`);
    } else {
        const addBtn = document.createElement("a");
        addBtn.setAttribute("href", `javascript:addPassenger(${oldId})`);
        addBtn.setAttribute("id", "addBtn");
        addBtn.textContent = "Přidat cestující";
        document.getElementById("passengers").appendChild(addBtn);
    }

    const closeBtn = document.querySelector(`#passenger${id} .title .closeBtn`);
    closeBtn.setAttribute("href", `javascript:removePassenger(${oldId})`);
    if (id != 2)
        document
            .querySelector(`#passenger${oldId} .title`)
            .appendChild(closeBtn);
    document.querySelector(`#passenger${id}`).remove();
}

function showPassengers() {
    let formatCheck = checkInputFormats();
    if (formatCheck.size > 0) {
        setErrorMessages(formatCheck);
    } else {
        setErrorMessages(formatCheck);

        const passengers = document.getElementById("passengers");
        passengers.style.opacity = 1;
        passengers.style.visibility = "visible";

        const submitBtn = document.querySelector("#flight-form .submitBtn");
        submitBtn.style.opacity = 1;
        submitBtn.style.visibility = "visible";
    }
}

function ageUpdatePassenger(element) {
    const curDate = new Date();
    const selectDate = new Date(element.value);

    const passengers = document.querySelectorAll(".passenger");
    let foundParent;
    for (var i = 0; i < passengers.length; i++) {
        if (passengers[i].contains(element)) {
            foundParent = passengers[i];
            break;
        }
    }

    if (selectDate.getTime() > curDate.getTime()) {
        foundParent.querySelector(".title p").textContent = `Věk: neplatný rok`;
    } else {
        const age = Math.floor(
            Math.abs(curDate - selectDate) / (1000 * 60 * 60 * 24 * 365)
        );
        if (age == 1) {
            foundParent.querySelector(
                ".title p"
            ).textContent = `Věk: ${age} rok`;
        } else if (age > 1 && age < 5) {
            foundParent.querySelector(
                ".title p"
            ).textContent = `Věk: ${age} roky`;
        } else if (age == 0 || age >= 5) {
            foundParent.querySelector(
                ".title p"
            ).textContent = `Věk: ${age} let`;
        }
    }
}

function checkInputFormats() {
    const passengers = document.getElementById("passengers");

    const from = document.getElementById("from-destination");
    const to = document.getElementById("to-destination");
    const depart = document.getElementById("depart");
    const ret = document.getElementById("return");
    const topArr = [from, to, depart, ret];

    const gender = document.querySelectorAll(".gender");
    const firstname = document.querySelectorAll(".firstname");
    const surname = document.querySelectorAll(".surname");
    const birth = document.querySelectorAll(".birth");
    const phone = document.querySelectorAll(".phone");
    const email = document.querySelectorAll(".email");
    const bottomArr = [gender, firstname, surname, birth, phone, email];

    const departDate = new Date(depart.value);
    const retDate = new Date(ret.value);
    const curDate = new Date();

    let isEmptyTopArr = [];
    let isEmptyArr = [];
    let errorArr = new Map();

    let phoneregex = new RegExp("^\\+\\d{1,3}\\d{7,12}$");
    let emailregex = new RegExp(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    for (let i = 0; i < topArr.length; i++)
        if (topArr[i].value === "") isEmptyTopArr.push(topArr[i]);

    if (isEmptyTopArr.length > 0) {
        var form = document.querySelector("form");
        errorArr.set(isEmptyTopArr, "Vyplňte prosím pole.");
    }
    if (from.value !== "" && to.value !== "" && from.value === to.value) {
        errorArr.set([from, to], "Destinace musí být odlišné.");
    }
    if (
        departDate > retDate ||
        Math.floor(Math.abs(retDate - departDate) / (1000 * 60 * 60 * 24)) == 0
    ) {
        errorArr.set([ret], "Přílet musí být alespoň o den později než odlet.");
    }
    if (Math.floor((departDate - curDate) / (1000 * 60 * 60 * 24)) < 3) {
        errorArr.set(
            [depart],
            "Odlet musí být alespoň o 3 dny později než dnes."
        );
    }

    if (window.getComputedStyle(passengers).opacity == 1) {
        for (let i = 0; i < bottomArr.length; i++) {
            for (let j = 0; j < bottomArr[i].length - 1; j++) {
                if (bottomArr[i][j].value === "")
                    isEmptyArr.push(bottomArr[i][j]);
            }
        }

        if (isEmptyArr.length > 0) {
            var form = document.querySelector("form");
            errorArr.set(isEmptyArr, "Vyplňte prosím pole.");
        }

        for (let i = 0; i < birth.length - 1; i++) {
            let currentDate = new Date();
            let birthDate = new Date(birth[i].value);
            const allowedAge = 6;
            if (
                Math.floor(
                    Math.abs(currentDate - birthDate) /
                        (1000 * 60 * 60 * 24 * 365)
                ) < allowedAge &&
                !(birthDate.getTime() > currentDate.getTime())
            ) {
                errorArr.set([birth[i]], "Cestujícím musí být víc jak 18 let.");
            }
        }

        for (let i = 0; i < birth.length - 1; i++) {
            let birthDate = new Date(birth[i].value);
            let currentDate = new Date();
            if (birthDate.getTime() > currentDate.getTime()) {
                errorArr.set([birth[i]], "Neplatný rok narození.");
            }
        }

        for (let i = 0; i < phone.length - 1; i++) {
            const formattedPhone = phone[i].value.split(" ").join("");
            if (phone[i].value !== "" && !phoneregex.test(formattedPhone)) {
                errorArr.set([phone[i]], "Telefonní číslo nemá platný formát.");
            }
        }

        for (let i = 0; i < email.length - 1; i++) {
            if (email[i].value !== "" && !emailregex.test(email[i].value)) {
                errorArr.set([email[i]], "Emailová adresa nemá platný formát.");
            }
        }
    }

    return errorArr;
}

function checkDateInput() {
    const dateInputs = document.querySelectorAll('input[type="date"]');
    for (var i = 0; i < dateInputs.length; i++) {
        if (dateInputs[i].value === "") {
            dateInputs[i].classList.add("empty");
        } else {
            dateInputs[i].classList.remove("empty");
        }
    }
}

function initDateInputListeners() {
    checkDateInput();
    const dateInputs = document.querySelectorAll('input[type="date"]');
    addDateInputListeners(document.querySelector(`#passenger1 .birth`));
    for (var i = 0; i < dateInputs.length; i++) {
        dateInputs[i].addEventListener("change", checkDateInput);
        dateInputs[i].addEventListener("input", checkDateInput);
    }
}

function addDateInputListeners(dateInput) {
    dateInput.addEventListener("change", function () {
        ageUpdatePassenger(this);
        checkDateInput();
    });
    dateInput.addEventListener("input", function () {
        ageUpdatePassenger(this);
        checkDateInput();
    });
}

function initInputListeners() {
    const startElements = document.querySelectorAll(
        "#from-destination-selectized, #to-destination-selectized, #depart, #return, #gender1, #firstname1, #surname1, #birth1, #phone1, #email1"
    );
    startElements.forEach((element) => {
        addInputListeners(element);
    });
}

function addInputListeners(inputField) {
    const events = ["change", "input", "blur"];
    events.forEach((event) => {
        inputField.addEventListener(event, function () {
            removeSpecificErrorMessage(this);
        });
    });
}

function removeSpecificErrorMessage(input) {
    const children = input.parentElement.children;
    for (let i = children.length - 1; i >= 0; i--) {
        if (children[i].classList.contains("error-msg")) {
            children[i].remove();
        }
    }
}

function setErrorMessages(errorMap) {
    const oldData = document.querySelectorAll(".error-msg");
    for (let i = 0; i < oldData.length; i++) {
        oldData[i].remove();
    }

    for (const [key, value] of errorMap) {
        for (var i = 0; i < key.length; i++) {
            const inputParent = key[i].parentElement;
            const children = inputParent.children;
            const isEmpty = true;
            for (let i = children.length - 1; i >= 0; i--) {
                if (children[i].classList.contains("error-msg")) {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                const errorMsg = document.createElement("p");
                errorMsg.textContent = value;
                errorMsg.setAttribute("class", "error-msg");

                inputParent.appendChild(errorMsg);
                setBottomByHeight(inputParent, ".error-msg");
            }
        }
    }
}

function setBottomByHeight(parent, elementClass) {
    const computedElement = parent.querySelector(elementClass);
    const elementHeight = computedElement.offsetHeight;
    computedElement.style.bottom = -(elementHeight + 5) + "px";
}

function submitForm() {
    const form = document.getElementById("orderForm");
    const errorMap = checkInputFormats();
    if (errorMap.size > 0) {
        setErrorMessages(errorMap);
    } else {
        const formData = new FormData(form);
        generateSummary(formData);
        for (const [key, value] of formData.entries()) {
            console.log(key, value);
        }
    }
}

function getNameByValue(value) {
    const country = countries.find((country) => country.value === value);
    return country ? country.name : "Chyba: Kód letiště nenalezen.";
}

function generateSummary(formData) {
    const flightSummary = document.getElementById("flight-summary");
    flightSummary.innerHTML = "";
    const passengersSummary = document.getElementById("passengers-summary");
    passengersSummary.innerHTML = "";

    const summaryTitle = document.querySelector("#summary h2");
    summaryTitle.style.visibility = "visible";
    summaryTitle.style.opacity = 1;

    const printBtnClear = document.querySelector(
        "#summary .information-summary .printBtn"
    );
    if (printBtnClear != null) printBtnClear.remove();

    const passengers = document.querySelectorAll(".form-el.passenger");
    const noOfPassengers = passengers.length - 1;
    let passengerElementCount;
    for (const [key, value] of formData.entries())
        if (/\d/.test(key)) passengerElementCount++;

    for (var i = 1; i <= noOfPassengers; i++) {
        const passSummary = document.querySelector(
            "#summary #passengers-summary"
        );

        const passenger = document.createElement("div");
        passenger.setAttribute("id", `passenger-summary-${i}`);

        const passengerTitle = document.createElement("h3");
        passengerTitle.textContent = `${i}. cestující`;
        passenger.appendChild(passengerTitle);

        passSummary.appendChild(passenger);
    }

    for (const [key, value] of formData.entries()) {
        let finalKey;
        let finalValue;
        if (!/\d/.test(key)) {
            switch (key) {
                case "from-destination":
                    finalKey = "Odkud";
                    finalValue = getNameByValue(value);
                    break;
                case "to-destination":
                    finalKey = "Kam";
                    finalValue = getNameByValue(value);
                    break;
                case "depart":
                    finalKey = "Čas odletu";
                    finalValue = value;
                    break;
                case "return":
                    finalKey = "Čas příletu";
                    finalValue = value;
                    break;
                default:
                    break;
            }
            let parent = document.querySelector(`#summary #flight-summary`);
            let text = document.createElement("p");
            text.textContent = finalKey + ": " + finalValue;
            parent.appendChild(text);
        } else {
            let withoutLetters = parseInt(key.replace(/\D/g, ""));
            let withoutDigits = key.replace(/[0-9]/g, "");

            switch (withoutDigits) {
                case "gender":
                    finalKey = "Pohlaví";
                    finalValue =
                        value === "f"
                            ? "žena"
                            : "muž";
                    break;
                case "firstname":
                    finalKey = "Jméno";
                    finalValue = value;
                    break;
                case "surname":
                    finalKey = "Příjmení";
                    finalValue = value;
                    break;
                case "birth":
                    finalKey = "Datum narození";
                    finalValue = value;
                    break;
                case "phone":
                    finalKey = "Telefonní číslo";
                    finalValue = value.split(" ").join("");
                    break;
                case "email":
                    finalKey = "E-mail";
                    finalValue = value;
                    break;

                default:
                    break;
            }
            let parent = document.querySelector(
                `#passenger-summary-${withoutLetters}`
            );
            let text = document.createElement("p");
            text.textContent = finalKey + ": " + finalValue;
            parent.appendChild(text);
        }
    }
    let parent = document.querySelector(`#summary .information-summary`);
    const print = document.createElement("a");
    print.textContent = "Vytisknout";
    print.setAttribute("href", "#");
    print.setAttribute("class", "printBtn noPrint");
    print.setAttribute("onclick", "window.print();");
    parent.appendChild(print);
}

function smoothScrollToSection(section) {
    const element = document.getElementById(section);
    const padding = 50;
    const headerOffset = padding;
    const elementPosition = element.getBoundingClientRect().top;
    const offsetPosition = elementPosition + window.scrollY - headerOffset;

    window.scrollTo({
        top: offsetPosition,
        behavior: "smooth",
    });
}

document.addEventListener("DOMContentLoaded", function () {
    $(".select-search").selectize({
        options: countries,
        optionGroupRegister: function (optgroup) {
            var group = {
                label: optgroup,
            };
            group[this.settings.optgroupValueField] = optgroup;
            return group;
        },
        optgroupField: "country",
        labelField: "name",
        searchField: ["name", "country"],
        sortField: "name",
        highlight: false,
        plugins: ["clear_button", "autofill_disable"],

        // Override the diacritics map
        diacritics: {
            a: "[aáàâäãåą]",
            c: "[cçćč]",
            d: "[dďđ]",
            e: "[eéèêëěę]",
            i: "[iíìîï]",
            n: "[nñňń]",
            o: "[oóòôöõ]",
            r: "[rř]",
            s: "[sšś]",
            t: "[tť]",
            u: "[uúùûüů]",
            y: "[yý]",
            z: "[zžźż]",
        },

        // Custom diacritic-insensitive tokenizer
        tokenize: function (query, respect_word_boundaries) {
            var diacritics = this.settings.diacritics;
            query = query
                .normalize("NFD")
                .replace(/[\u0300-\u036f]/g, "")
                .toLowerCase();

            if (!query || !query.length) return [];
            var i, n, regex, letter;
            var tokens = [];
            var words = query.split(/ +/);

            for (i = 0, n = words.length; i < n; i++) {
                var word = words[i];
                for (letter in diacritics) {
                    if (diacritics.hasOwnProperty(letter)) {
                        word = word.replace(
                            new RegExp(letter, "g"),
                            diacritics[letter]
                        );
                    }
                }
                regex = escape_regex(word);
                if (respect_word_boundaries) regex = "\\b" + regex;
                tokens.push({
                    string: words[i],
                    regex: new RegExp(regex, "i"),
                });
            }
            return tokens;
        },

        // Override the prepareSearch method to normalize item properties
        prepareSearch: function (query, settings) {
            var self = this;
            query = self.tokenize(query, settings.respect_word_boundaries);
            var tokens = query.tokens;

            settings = settings || {};
            var fields = settings.fields || self.settings.searchField;
            var score = settings.score || self.getScoreFunction(query);

            var iterator = function (item, result) {
                for (var i = 0, n = fields.length; i < n; i++) {
                    var field = fields[i];
                    var value =
                        self.settings.sortField === field
                            ? item[field].toLowerCase()
                            : item[field];
                    if (value) {
                        value = value
                            .normalize("NFD")
                            .replace(/[\u0300-\u036f]/g, "")
                            .toLowerCase();
                        item[field] = value;
                    }
                }
                result.push(item);
            };

            var results = [];
            self.settings.options.forEach(function (item) {
                iterator(item, results);
            });

            return {
                options: results,
                query: query,
                score: score,
            };
        },
    });
    passengerDiv = document.getElementById("passenger-clone");
    initDateInputListeners();
    initInputListeners();
});
