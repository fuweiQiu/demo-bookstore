package demo.databaseconfig.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import demo.databaseconfig.entity.Persistable;

@Aspect
@Component
public class PersistableAspect {
    @Pointcut("execution(" +
            "public * *+.save(demo.databaseconfig.entity.Persistable)" +
            ") || execution(" +
            "public * *+.saveAndFlush(demo.databaseconfig.entity.Persistable)" +
            ") || execution(" +
            "public * *+.saveAll(Iterable<demo.databaseconfig.entity.Persistable>)" +
            ")")
    public void savePointcut() {
    }

    @Pointcut("execution(" +
            "public * *+.update(demo.databaseconfig.entity.Persistable)" +
            ") || execution(" +
            "public * *+.updateAndFlush(demo.databaseconfig.entity.Persistable)" +
            ") || execution(" +
            "public * *+.updateAll(Iterable<demo.databaseconfig.entity.Persistable>)" +
            ")")
    public void updatePointcut() {
    }

    @Before("savePointcut()")
    public void beforeSave(JoinPoint joinPoint) {
        setSave(joinPoint.getArgs()[0], true);
    }

    @SuppressWarnings("unchecked")
    private void setSave(Object entity, boolean isSave) {
        if (entity instanceof Iterable) {
            Iterable<Persistable<?>> persistableIterable = (Iterable<Persistable<?>>) entity;
            for (Persistable<?> persistable : persistableIterable) {
                if (persistable.getSave() == null) {
                    persistable.setSave(isSave);
                }
            }
        } else if (((Persistable<?>) entity).getSave() == null) {
            ((Persistable<?>) entity).setSave(isSave);
        }
    }

    @Before("updatePointcut()")
    public void beforeUpdate(JoinPoint joinPoint) {
        setSave(joinPoint.getArgs()[0], false);
    }
}
