package hostelp_backend.adapter.out.persistence;

import hostelp_backend.domain.port.out.TransactionRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@Component
public class SpringTransactionRunner implements TransactionRunner {

    private final TransactionTemplate readWrite;
    private final TransactionTemplate readOnly;

    public SpringTransactionRunner(PlatformTransactionManager txManager) {
        this.readWrite = new TransactionTemplate(txManager);
        this.readOnly = new TransactionTemplate(txManager);
        this.readOnly.setReadOnly(true);
    }

    @Override
    public <T> T execute(Supplier<T> work) {
        return readWrite.execute(status -> work.get());
    }

    @Override
    public <T> T executeReadOnly(Supplier<T> work) {
        return readOnly.execute(status -> work.get());
    }
}
