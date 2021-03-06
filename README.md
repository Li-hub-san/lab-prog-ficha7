# Ficha de exercícios 7

#### Ex.1: Considere o seguinte diagrama.

![Diagrama](https://github.com/Li-hub-san/lab-prog-ficha7/blob/master/src/main/resources/uml_diagram.png)

Crie uma `API` que suporte e guarde a informação presente no diagrama de classes. Deverácriar os seguintes `endpoints`:

<table>
<tr>
<th>Pessoa</th>
<td></td>
<th>Empresa</th>
</tr>
<tr>
<td>/addPessoa</td>
<td></td>
<td>/addEmpresa</td>
</tr>
<tr>
<td>/updatePessoa</td>
<td></td>
<td>/updateEmpresa</td>
</tr>
<tr>
<td>/deletePessoa/{id}</td>
<td></td>
<td>/deleteEmpresa/{id}</td>
</tr>
<tr>
<td>/getAllPessoas</td>
<td></td>
<td>/getAllEmpresas</td>
</tr>
<tr>
<td>/getPessoaById/{id}</td>
<td></td>
<td>/getEmpresaById/{id}</td>
</tr>
</table>

Notas: Todas as verificações devem ser feitas:

:white_check_mark: Não pode ser permitido inserir/atualizar o valor do nome de uma pessoa em vazio ou com espaços;

:white_check_mark: Cada elemento deverá ter um identificador único e gerado automaticamente;

:white_check_mark: Remover uma empresa implica remover todas as pessoas.
