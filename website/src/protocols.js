/*
Add example protocols here.
The object key is the protocol name in the dropdown menu.
The associated value is the Subzero code that will be loaded into the editor.

Use the following formatting:

'Protocol name':
`
Subzero code
`,

*/

export default {

'Dlog equality':
`
witness: k;
b = a^k & h = g^k
`,

'ElGamal plaintext':
`
witness: x,r;
g^x * h^r = C_1 & h^r = C_2
`,

'Pedersen commitment with range proof':
`
[Pedersen commitment with range proof]

pp : h_1, h_2, g
witness : m_1, m_2 ,r

C_1 = h_1^m_1 * h_2^m_2 * g^r & 20 <= m_1 + m_2 <= 100
`,

'Basic proof of partial knowledge':
`
[Partial knowledge]

witness: x,r;
g^x * h^r = C
& (h^r = C_2 | h^x = C_2)
`,


'Pointcheval Sanders credential':
`
[Pointcheval Sanders credential showing]

witness: age, pos, r

e(sigma_1', X~) * e(sigma_1', Y_1~^age * Y_2~^pos) * e(sigma_1', g~)^r = e(sigma_2', g~) // valid signature
& (age < 18 | pos = 17) // young or student
`,


}
