package com.matricula.matricula.AOP;

import com.matricula.matricula.Repository.LogRepositorio;
import com.matricula.matricula.Entity.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAOP {

    @Autowired
    LogRepositorio logRepositorio;

    // Transacciones de Persona

    @Before("execution(* com.matricula.matricula.Repository.PersonaRepositorio.findAll(..))")
    public void logbeforeV1(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Persona");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.PersonaRepositorio.save(..))")
    public void logbeforeV2(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Persona");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.PersonaRepositorio.delete(..))")
    public void logbeforeV3(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Persona");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.PersonaRepositorio.findById(..))")
    public void logbeforeV4(JoinPoint jointPoint) {
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Persona");
        logRepositorio.save(log);
    }

    // Transacciones de Periodo

    @Before("execution(* com.matricula.matricula.Repository.PeriodoRepositorio.findAll(..))")
    public void logbeforeV5(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Periodo");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.PeriodoRepositorio.save(..))")
    public void logbeforeV6(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Periodo");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.PeriodoRepositorio.delete(..))")
    public void logbeforeV7(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Periodo");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.PeriodoRepositorio.findById(..))")
    public void logbeforeV8(JoinPoint jointPoint) {
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Periodo");
        logRepositorio.save(log);
    }

    // Transacciones de Materia

    @Before("execution(* com.matricula.matricula.Repository.MateriaRepositorio.findAll(..))")
    public void logbeforeV9(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Materia");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.MateriaRepositorio.save(..))")
    public void logbeforeV10(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Materia");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.MateriaRepositorio.delete(..))")
    public void logbeforeV11(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Materia");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.MateriaRepositorio.findById(..))")
    public void logbeforeV12(JoinPoint jointPoint) {
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Materia");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.MateriaRepositorio.findAllByPeriodoId(..))")
    public void logbeforeV(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Periodo");
        logRepositorio.save(log);
    }

    // Transacciones de Matricula

    @Before("execution(* com.matricula.matricula.Repository.MatriculaRepositorio.findAll(..))")
    public void logbeforeV13(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Matricula");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.MatriculaRepositorio.save(..))")
    public void logbeforeV14(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Matricula");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.MatriculaRepositorio.delete(..))")
    public void logbeforeV15(JoinPoint jointPoint){
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Matricula");
        logRepositorio.save(log);
    }

    @Before("execution(* com.matricula.matricula.Repository.MatriculaRepositorio.findById(..))")
    public void logbeforeV16(JoinPoint jointPoint) {
        Log log = new Log();
        log.setFecha(new Date());
        log.setMetodo(jointPoint.getSignature().getName());
        log.setTransaccion("Matricula");
        logRepositorio.save(log);
    }
}
