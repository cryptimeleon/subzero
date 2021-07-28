function formatFunction(func) {
    let functionRepr = func.name + ': ';
    let delimiter = '';
    func.parameterTypes.forEach(function(parameterType) {
        functionRepr += delimiter;
        functionRepr += parameterType;
        delimiter = ', ';
    });
    functionRepr += ' -> ' + func.returnType;
    return functionRepr;
}

function formatVariable(func) {
    let varRepr = func.name + ": " + func.type;
    if (func.group) varRepr += " (" + func.group + ")";
    return varRepr;
}

function updateEnvironment(environment) {
    console.log(environment);
    return;
}