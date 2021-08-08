export function validateCode(codeResourceId, callback) {
    const request = jQuery.ajax({
        url: `xtext-service/validate?resource=${codeResourceId}`
    });
        
    request.done((result) => {
        const issues = result.issues;
        const isValid = issues.length === 0 || !issues.some((issue) => {
            return issue.severity === 'error';
        });
        callback(isValid, issues);
    });
}