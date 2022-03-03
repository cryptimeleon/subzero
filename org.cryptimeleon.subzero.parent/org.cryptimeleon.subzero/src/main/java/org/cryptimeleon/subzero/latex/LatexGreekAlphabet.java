package org.cryptimeleon.subzero.latex;

import java.util.Map;

/*
 * Helper class to get the LaTeX command for Greek letters
 */
public class LatexGreekAlphabet {

	// Some LaTeX commands are slightly different, so a map is needed as opposed to a set
	private static final Map<String, String> alphabet = Map.ofEntries(
		Map.entry("alpha", "alpha"),
		Map.entry("beta", "beta"),
		Map.entry("gamma", "gamma"),
		Map.entry("Gamma", "Gamma"),
		Map.entry("delta", "delta"),
		Map.entry("Delta", "Delta"),
		Map.entry("eps", "varepsilon"),
		Map.entry("epsilon", "varepsilon"),
		Map.entry("zeta", "zeta"),
		Map.entry("eta", "eta"),
		Map.entry("theta", "theta"),
		Map.entry("Theta", "Theta"),
		Map.entry("iota", "iota"),
		Map.entry("kappa", "kappa"),
		Map.entry("lambda", "lambda"),
		Map.entry("Lambda", "Lambda"),
		Map.entry("mu", "mu"),
		Map.entry("nu", "nu"),
		Map.entry("xi", "xi"),
		Map.entry("Xi", "Xi"),
		Map.entry("pi", "pi"),
		Map.entry("Pi", "Pi"),
		Map.entry("rho", "rho"),
		Map.entry("sigma", "sigma"),
		Map.entry("Sigma", "Sigma"),
		Map.entry("tau", "tau"),
		Map.entry("ups", "upsilon"),
		Map.entry("upsilon", "upsilon"),
		Map.entry("Ups", "Upsilon"),
		Map.entry("Upsilon", "Upsilon"),
		Map.entry("phi", "phi"),
		Map.entry("Phi", "Phi"),
		Map.entry("chi", "chi"),
		Map.entry("psi", "psi"),
		Map.entry("Psi", "Psi"),
		Map.entry("omega", "omega"),
		Map.entry("Omega", "Omega")
	);

	public static String getCommand(String letter) {
		String command = alphabet.get(letter);
		return command == null ? null : "\\" + command;
	}
}
