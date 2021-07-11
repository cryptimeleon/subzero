/*
Add example protocols here.
The object key is the protocol name in the dropdown menu.
The associated value is the Subzero code that will be loaded into the editor

Use the following formatting:

"Protocol name":
`
Subzero code
`,

*/

let PROTOCOLS = {

"Simple protocol":
`
witness: x,r;
g^x * h^r = C & h^r = C2
`,


"Pedersen commitment":
`
[Pedersen commitment]

pp : h_1 , h_2 , g
witness : m_1 , m_2 , r

C_1 = h_1 ^ m_1 * h_2 ^ m_2 * g ^ r & 20 <= m_1 + m_2 <= 100
`,


"Proof of partial knowledge":
`
[Partial knowledge]

witness: x,r;
g^x * h^r = C
& (h^r = C2 | h^x = C2)
`,


"Pointcheval Sanders signature":
`
[Pointcheval Sanders signature]

witness: age_, pos_, r_

e(sigma_1', X~) * e(sigma_1', Y_1~^age_ * Y_2~^pos_) * e(sigma_1', g~)^r_ = e(sigma_2', g~) // valid signature
& (age_ < 18 | pos_ = 17) // young or student
`,


}