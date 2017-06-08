package Modelos;

public class Cliente extends Pessoa {

    @Override
    public void inserir() {
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void carregar() {
        carregarPorId(this.id);
    }

    @Override
    public void remover() {
    }

    @Override
    public void carregarPorId(int id) {
    }
}
