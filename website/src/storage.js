const storage = window.localStorage;

export function loadFromStorage(key, defaultValue) {
    const storedValue = storage.getItem(key);
    let returnValue;

    if (storedValue === null) {
        returnValue = defaultValue;
        storage.setItem(key, defaultValue);
    } else if (storedValue === 'true') {
        returnValue = true;
    } else if (storedValue === 'false') {
        returnValue = false;
    } else if (!isNaN(storedValue)) {
        returnValue = parseInt(storedValue);
    } else {
        returnValue = storedValue;
    }

    return returnValue;
}

export function saveToStorage(key, newValue) {
    storage.setItem(key, newValue);
}