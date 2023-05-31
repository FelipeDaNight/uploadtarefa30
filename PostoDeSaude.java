package uploadtarefa30;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

class Pessoa {
    private int id;
    private String sexo;
    private int idade;
    private boolean gestante;
    private boolean lactante;
    private boolean necessidadeEspecial;

    public Pessoa(int id, String sexo, int idade, boolean gestante, boolean lactante, boolean necessidadeEspecial) {
        this.id = id;
        this.sexo = sexo;
        this.idade = idade;
        this.gestante = gestante;
        this.lactante = lactante;
        this.necessidadeEspecial = necessidadeEspecial;
    }

    public int getId() {
        return id;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }

    public boolean isGestante() {
        return gestante;
    }

    public boolean isLactante() {
        return lactante;
    }

    public boolean isNecessidadeEspecial() {
        return necessidadeEspecial;
    }
}

public class PostoDeSaude {
    private static final int CAPACIDADE_ATENDIMENTO_DIA = 100;

    public static void main(String[] args) {
        Deque<Pessoa> filaAtendimento = new ArrayDeque<>();
        Random random = new Random();

        // Adiciona pessoas à fila de atendimento
        for (int i = 0; i < CAPACIDADE_ATENDIMENTO_DIA; i++) {
            int id = i + 1;
            String sexo = random.nextBoolean() ? "M" : "F";
            int idade = random.nextInt(100);
            boolean gestante = random.nextBoolean();
            boolean lactante = random.nextBoolean();
            boolean necessidadeEspecial = random.nextBoolean();

            Pessoa pessoa = new Pessoa(id, sexo, idade, gestante, lactante, necessidadeEspecial);
            filaAtendimento.addLast(pessoa);
        }

        int grupo = 1;
        int atendidos = 0;

        
        while (!filaAtendimento.isEmpty() && atendidos < CAPACIDADE_ATENDIMENTO_DIA) {
            int pessoasNoGrupo = Math.min(grupo, filaAtendimento.size());

            System.out.println("Grupo " + grupo + ":");
            for (int i = 0; i < pessoasNoGrupo; i++) {
                Pessoa pessoa = filaAtendimento.removeFirst();
                atenderPessoa(pessoa);
                atendidos++;
            }

            grupo++;
        }

        
        System.out.println("\nPessoas não atendidas:");
        for (Pessoa pessoa : filaAtendimento) {
            System.out.println("ID: " + pessoa.getId() + ", Prioridade: " + calcularPrioridade(pessoa));
        }
    }

    private static void atenderPessoa(Pessoa pessoa) {
        System.out.println("ID: " + pessoa.getId() + ", Prioridade: " + calcularPrioridade(pessoa));
        
    }

    private static int calcularPrioridade(Pessoa pessoa) {
        if (pessoa.getIdade() >= 60) {
            return 1;
        } else if (pessoa.isNecessidadeEspecial()) {
            return 2;
        } else if (pessoa.isGestante() || pessoa.isLactante()) {
            return 3;
        } else {
            return 0;
        }
    }
}
