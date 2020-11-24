package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo repo;
    
    @InjectMocks
    private TaskController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTaskSemDescricao() {
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.now());
        try {
            controller.save(tarefa);
            Assert.fail("Não deveria chegar nesse ponto, pois o try não dever ser executado, pois está com falha");
        } catch (Exception e){
            Assert.assertEquals("Fill the task description",e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTaskComDataPassada(){
        Task tarefa = new Task();
        tarefa.setTask("Comprar leite");
        tarefa.setDueDate(LocalDate.of(2010,12,25));
        try {
            controller.save(tarefa);
            Assert.fail("Não deveria chegar nesse ponto, pois o try não dever ser executado, pois está com falha");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData(){
        Task tarefa = new Task();
        tarefa.setTask("Comprar pão");
        tarefa.setDueDate(null);
        try {
            controller.save(tarefa);
            Assert.fail("Não deveria chegar nesse ponto, pois o try não dever ser executado, pois está com falha");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void deveSalvarTarefaCompleta(){
        Task tarefa = new Task();
        tarefa.setDueDate(LocalDate.of(2030,12,23));
        tarefa.setTask("Comprar velas");
        try {
            controller.save(tarefa);
            Mockito.verify(repo).save(tarefa);//Faz a checagem do método acessado e entidade passado.
        } catch (ValidationException e) {
            Assert.fail("Não deveria chegar nesse ponto, pois a tarefa está completa.");
        }
    }
}
