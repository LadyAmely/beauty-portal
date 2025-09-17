package org.shop.beautyportal.saleschannels.adapters.importcsv;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.shop.beautyportal.saleschannels.ports.input.dto.request.CreateQuarterReportRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuarterReportImportService {

    /**
     * Converts uploaded CSV into CreateQuarterReportRequest.
     * CSV must have a header: month,channel,amountInputCcy
     */
    public CreateQuarterReportRequest fromCsv(MultipartFile file,
                                              UUID distributorId,
                                              Integer year,
                                              Integer quarter,
                                              String currency,
                                              Integer newClients) {
        try (var reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            List<QuarterLineCsv> rows = new CsvToBeanBuilder<QuarterLineCsv>(reader)
                    .withType(QuarterLineCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            var req = new CreateQuarterReportRequest();
            req.setDistributorId(distributorId);
            req.setYear(year);
            req.setQuarter(quarter);
            req.setInputCurrency(currency);
            req.setNewClients(newClients == null ? 0 : newClients);

            req.setLines(rows.stream().map(this::toDto).toList());
            return req;

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse CSV: " + e.getMessage(), e);
        }
    }

    private CreateQuarterReportRequest.QuarterLineDTO toDto(QuarterLineCsv row) {
        var dto = new CreateQuarterReportRequest.QuarterLineDTO();
        dto.setMonth(row.getMonth());
        dto.setChannel(row.getChannel());
        dto.setAmountInputCcy(row.getAmountInputCcy());
        return dto;
    }
}

