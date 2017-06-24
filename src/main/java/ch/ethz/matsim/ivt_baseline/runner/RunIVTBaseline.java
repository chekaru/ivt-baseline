package ch.ethz.matsim.ivt_baseline.runner;

import ch.ethz.matsim.ivt_baseline.calibration.scoring.IVTCalibrationConfigGroup;
import ch.ethz.matsim.ivt_baseline.replanning.BlackListedTimeAllocationMutatorConfigGroup;
import ch.ethz.matsim.ivt_baseline.replanning.BlackListedTimeAllocationMutatorStrategyModule;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

public class RunIVTBaseline {
    static public void main(String[] args) {
        String configFile = args[0];

        // Configuration

        Config config = ConfigUtils.loadConfig(configFile, new BlackListedTimeAllocationMutatorConfigGroup(), new IVTCalibrationConfigGroup());
        Scenario scenario = ScenarioUtils.loadScenario(config);

        // Controller setup

        Controler controler = new Controler(scenario);
        controler.addOverridingModule(new BlackListedTimeAllocationMutatorStrategyModule());
        controler.addOverridingModule(new IVTBaselineScoringModule());
        controler.addOverridingModule(new IVTBaselineCalibrationModule());

        // Run

        controler.run();
    }
}
