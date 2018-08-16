package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.mock;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.spring.tx.annotation.Transactional;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Person;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyVersion;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db.HibernatePolicyRepository;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Replaces(HibernatePolicyRepository.class)
@Requires(env = Environment.TEST)
@Singleton
public class MockPolicyRepository implements PolicyRepository {

    private Map<String, Policy> policyMap = init();

    @Transactional
    @Override
    public Optional<Policy> findByNumber(String number) {
        return Optional.ofNullable(policyMap.get(number));
    }

    @Transactional
    @Override
    public void add(Policy policy) {
        policyMap.put(policy.getNumber(), policy);
    }

    private Map<String, Policy> init() {
        Map<String, Policy> map = new ConcurrentHashMap<>();

        map.put("1234", new Policy(1L, "1234", new HashSet<>(
                Collections.singletonList(new PolicyVersion(2L,
                        null,
                        1L,
                        "HFI",
                        new Person("Mary", "Smith", "11111111111"),
                        "1234",
                        DateRange.between(LocalDate.of(2018, 2, 3), LocalDate.of(2019, 2, 3)),
                        DateRange.between(LocalDate.of(2018, 2, 3), LocalDate.of(2019, 2, 3)),
                        new HashSet<>(),
                        BigDecimal.TEN
                )))));
        map.put("1235", new Policy(2L, "1235", new HashSet<>(
                Collections.singletonList(new PolicyVersion(1L,
                        null,
                        1L,
                        "HFI",
                        new Person("John", "Smith", "11111111111"),
                        "1234",
                        DateRange.between(LocalDate.of(2018, 2, 3), LocalDate.of(2019, 2, 3)),
                        DateRange.between(LocalDate.of(2018, 2, 3), LocalDate.of(2019, 2, 3)),
                        new HashSet<>(),
                        BigDecimal.TEN
                ))
        )));
        map.put("1236", new Policy(3L, "1236", new HashSet<>(
                Collections.singletonList(new PolicyVersion(3L,
                        null,
                        1L,
                        "HFI",
                        new Person("Johny", "Dip", "11111111111"),
                        "1234",
                        DateRange.between(LocalDate.of(2018, 2, 3), LocalDate.of(2019, 2, 3)),
                        DateRange.between(LocalDate.of(2018, 2, 3), LocalDate.of(2019, 2, 3)),
                        new HashSet<>(),
                        BigDecimal.TEN
                ))
        )));

        return map;
    }
}
