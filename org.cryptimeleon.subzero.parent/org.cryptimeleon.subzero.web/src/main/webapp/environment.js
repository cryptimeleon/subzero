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
    return func.name + ": " + func.type;
}

function updateEnvironment(environment) {
    console.log(environment);

    console.log("Predefined functions");
    environment.predefinedFunctions.forEach(function(func) {
        console.log(formatFunction(func));
    });

    console.log("User functions");
    environment.userFunctions.forEach(function(func) {
        console.log(formatFunction(func));
    });

    console.log("Public parameter variables");
    environment.publicParameterVariables.forEach(function(variable) {
        console.log(formatVariable(variable));
    });

    console.log("Witness variables");
    environment.witnessVariables.forEach(function(variable) {
        console.log(formatVariable(variable));
    });

    console.log("Common input variables");
    environment.commonInputVariables.forEach(function(variable) {
        console.log(formatVariable(variable));
    });
}