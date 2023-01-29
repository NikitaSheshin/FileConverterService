package Becnhmark;

import converter.fileconverter.FileConverter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApplicationTest {
    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(ApplicationTest.class.getSimpleName())
                .forks(1)
                .addProfiler("gc")
                .build()).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void json2xmlTest()
            throws JAXBException, IOException {
        FileConverter.convert(new String[]{"src/test/resources/files/CorrectJson.json",
                "src/test/resources/files/ResultXmlForTest.xml"});
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void xml2jsonTest()
            throws JAXBException, IOException {
        FileConverter.convert(new String[]{"src/test/resources/files/CorrectXml.xml",
                "src/test/resources/files/ResultJsonForTest.json"});
    }
}
