document.getElementById('cadastroForm').addEventListener('submit', cadastrarJogo);
var result = 0;
function cadastrarJogo(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const cpf = document.getElementById('cpf').value;
    const rg = document.getElementById('rg').value;
    const endereco = document.getElementById('endereco').value;

    fetch('http://localhost:8080/alunos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, cpf, rg, endereco }),
    })
        .then(response => response.json())
        .then(data => {
            alert('Aluno cadastrado com sucesso!');
            document.getElementById('cadastroForm').reset();            
        })
        .catch(error => {
            console.error('Erro ao cadastrar aluno:', error);
        });
}

function pesquisarJogo() {
    const searchId = document.getElementById('searchId').value;

    fetch(`http://localhost:8080/alunos/${searchId}`)
        .then(response => {
            if (response.status === 404) {
                return Promise.reject('Aluno não encontrado');
                result = 0;
            }
            return response.json();
        })
        .then(data => {
            result = 1;
            document.getElementById('name').value = `${data.name}`;
            document.getElementById('cpf').value = `${data.cpf}`;
            document.getElementById('rg').value = `${data.rg}`;
            document.getElementById('endereco').value = `${data.endereco}`;
        })
        .catch(error => {
            console.error('Erro ao pesquisar aluno:', error);
            const resultadoPesquisa = document.getElementById('resultadoPesquisa');
            resultadoPesquisa.innerHTML = 'Aluno não encontrado.';
        });
}

function atualizarJogo() {
    pesquisarJogo();
    if (result == 1) {
        const name = document.getElementById('name').value;
        const cpf = document.getElementById('cpf').value;
        const rg = document.getElementById('rg').value;
        const endereco = document.getElementById('endereco').value;
        const searchId = document.getElementById('searchId').value;

        fetch(`http://localhost:8080/alunos/${searchId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, cpf, rg, endereco }),
        })
            .then(response => response.json())
            .then(data => {
                alert('Aluno atualizado com sucesso!');
                document.getElementById('cadastroForm').reset();                
            })
            .catch(error => {
                console.error('Erro ao atualizar aluno:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum aluno foi alterado. Favor pesquisar aluno a ser alterado !!!');
    }
}

function excluirJogo(){
	pesquisarJogo() 
	
    pesquisarJogo();
    if (result == 1) {

        const searchId = document.getElementById('searchId').value;

        fetch(`http://localhost:8080/alunos/${searchId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, cpf, rg, endereco }),
        })
            .then(response => response.json())
            .then(data => {
                alert('Aluno excluido com sucesso!');
                document.getElementById('cadastroForm').reset();                
            })
            .catch(error => {
                console.error('Erro ao excluido aluno:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum aluno foi excluido. Favor pesquisar aluno a ser excluido !!!');
    }
}