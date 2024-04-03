# Lisp-machine-nsu

![image](https://github.com/chiguaua/Lisp-Machine/assets/69720999/df9cd7a2-8e52-483f-8fcf-219e42d0ca6b)


<p>This repository contains framework designed for translating lisp code into java . The framework allows users to run Lisp code stored in a specified file and generate corresponding Java code as output.</p>

<h2>Installation</h2>

<p>To use this framework, you will need Java installed on your system. You can download the latest release of the framework from the <a href="https://github.com/chiguaua/Test-Framefork/releases">releases page</a>.</p>

<h2>Usage</h2>

<p>Once you have downloaded the framework, you can run it using the following command:</p>

<pre><code>java -jar ./target/original-LispMachine-1.0.jar -test -file InpName OutputName -code row_lisp_code $$</code></pre>

<p>Replace <code>InpName</code> with the input file containing the Lisp code you want to test and <code>OutputName</code> with the desired name for the generated Java code file.</p>

<h2>Example</h2>

<p>Let's say you have a Lisp code snippet stored in a file named <code>resources/test.list</code> and you want to generate Java code named <code>TestOut.java</code>. You would run the following command:</p>

<pre><code>java -jar ./target/original-LispMachine-1.0.jar -test -file resources/test.list TestOut -code row_lisp_code $$</code></pre>

<h2>Contributing</h2>

<p>Contributions to this test framework are welcome! If you encounter any issues or have suggestions for improvement, please open an issue or submit a pull request.</p>

<h2>License</h2>

<p>This project is licensed under the MIT License - see the <a href="LICENSE">LICENSE</a> file for details.</p>
