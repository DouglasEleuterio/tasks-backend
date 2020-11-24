package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void checaSeDataEhFutura(){
        LocalDate newDate = LocalDate.of(2023,12,01);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(newDate));
    }

    @Test
    public void checaSeDataEhAtual(){
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(LocalDate.now()));
    }

    @Test
    public void checaSeDataEhPassada(){
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(LocalDate.of(2010,01,01)));
    }
}
