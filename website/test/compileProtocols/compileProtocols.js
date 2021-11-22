import process from 'process';
import request from 'request';
import JSZip from 'jszip';
import fs from 'fs';
import protocols from './protocols.js';

function generationRequest(protocol, callback) {
    const url = 'http://localhost:8080/subzero/xtext-service/generate?resource=testing.code.sub0';

    request.post({
        url: url,
        form: {
            fullText: protocol
        }
    }, (error, response, body) => {
        if (!error && response.statusCode == 200) {
            callback(JSON.parse(body));
        } else {
            process.exit(1);
        }
    });
}

function saveProject(protocolName, project) {
    const filename = '../temp/' + protocolName + '.zip';

    let projectZip = new JSZip();
    createProjectZip(projectZip, project);
    projectZip.generateNodeStream({streamFiles:true}).pipe(fs.createWriteStream(filename));
}

function createProjectZip(zip, currentFolder) {
    for (const [name, contents] of Object.entries(currentFolder)) {
        if (typeof contents === 'string') {
            if (name.endsWith('.jar')) {
                zip.file(name, contents, {base64: true});
            } else if (!name.includes('.')) {
                zip.file(name, contents, {unixPermissions: '775'});
            } else {
                zip.file(name, contents);
            }
        } else {
            createProjectZip(zip.folder(name), contents);
        }
    }
}

const stream = fs.createWriteStream('protocolNames.txt', {flags: 'a'});

for (const [protocolName, protocol] of Object.entries(protocols)) {

    stream.write(protocolName + '\n');
    
    generationRequest(protocol.trim(), (body) => {
        saveProject(protocolName, body);
    });
}

stream.end();