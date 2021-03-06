package io.penguinstats.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.penguinstats.enums.Server;
import io.penguinstats.service.ItemDropService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class UpdateCurrentDropMatrixTask implements Task {

	@Autowired
	private ItemDropService itemDropService;

	@Scheduled(fixedRate = 912345)
	@Override
	public void execute() {
		log.info("execute UpdateCurrentDropMatrixTask");

		itemDropService.updateDropMatrixElements(null, false);

		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (Server server : Server.values()) {
			singleThreadExecutor.execute(() -> itemDropService.refreshGlobalDropMatrixElements(server, false));
		}
	}

}
