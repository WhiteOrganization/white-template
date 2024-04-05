package org.white_sdev.templates.white_template.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@lombok.extern.slf4j.Slf4j
@SuppressWarnings("unused")
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createStoredProcedure() {
        String logID="::createStoredProcedure(): ";
        logStart(logID);
        String sp = "CREATE ALIAS CalculateSum AS $$ "
                + "int CalculateSum(int num1, int num2) { "
                + "    return num1 + num2; "
                + "} $$";
        log.warn("{}Creating Stored Procedure sp:{}", logID, sp);
        jdbcTemplate.execute(sp);
        log.info("{}Finish - SP Created", logID);
    }

    public int callStoredProcedure(int num1, int num2) {
        String logID="::callStoredProcedure(): ";
        logStart(logID);
        @SuppressWarnings("all")
        int returnValue = jdbcTemplate.queryForObject("CALL CalculateSum(?, ?)", Integer.class, num1, num2);
        log.info("{}Finish - returnValue:{}", logID, returnValue);
        return returnValue;
    }

    private static void logStart(String logID) {
        log.trace("{}Start", logID);
    }
}
