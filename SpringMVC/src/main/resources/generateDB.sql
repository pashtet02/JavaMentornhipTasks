CREATE DATABASE `spring_mvc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `event`
(
    `id`             bigint       NOT NULL,
    `creation_dt_tm` datetime(6)  DEFAULT NULL,
    `details`        varchar(255) DEFAULT NULL,
    `event_date`     date         DEFAULT NULL,
    `event_time`     time         DEFAULT NULL,
    `location`       varchar(255) DEFAULT NULL,
    `name`           varchar(254) NOT NULL,
    `price`          double       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_mt8ulcc4k7fnc56rxaeu1sa33` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
-- some random line to invoke jenkins
CREATE TABLE `event_ticket_list`
(
    `event_id`       bigint NOT NULL,
    `ticket_list_id` bigint NOT NULL,
    UNIQUE KEY `UK_67uttdqr0x9iomco957hvfl2o` (`ticket_list_id`),
    KEY `FKh5y6m46mivdjqkkna14h5493v` (`event_id`),
    CONSTRAINT `FKh5y6m46mivdjqkkna14h5493v` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
    CONSTRAINT `FKq6g1gwdfllrsn85u4mhd0s41q` FOREIGN KEY (`ticket_list_id`) REFERENCES `ticket` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `ticket`
(
    `id`            bigint NOT NULL,
    `creation_date` datetime(6) DEFAULT NULL,
    `event_id`      bigint      DEFAULT NULL,
    `user_id`       bigint      DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKfytuhjopeamxbt1cpudy92x5n` (`event_id`),
    KEY `FKdvt57mcco3ogsosi97odw563o` (`user_id`),
    CONSTRAINT `FKdvt57mcco3ogsosi97odw563o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKfytuhjopeamxbt1cpudy92x5n` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `user`
(
    `id`         bigint NOT NULL,
    `active`     bit(1) NOT NULL,
    `password`   varchar(255) DEFAULT NULL,
    `username`   varchar(255) DEFAULT NULL,
    `account_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKh44k9woqqx19dc8k73tjg8hbb` (`account_id`),
    CONSTRAINT `FKh44k9woqqx19dc8k73tjg8hbb` FOREIGN KEY (`account_id`) REFERENCES `user_account` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `user_account`
(
    `id`    bigint NOT NULL,
    `money` double DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `user_role`
(
    `user_id` bigint NOT NULL,
    `roles`   varchar(255) DEFAULT NULL,
    KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
    CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `user_tickets`
(
    `user_id`    bigint NOT NULL,
    `tickets_id` bigint NOT NULL,
    UNIQUE KEY `UK_qbc56ojmvhpcn1h4i3r1k29lg` (`tickets_id`),
    KEY `FK3mrbqyn3ga9avpqwj4eqfp8v4` (`user_id`),
    CONSTRAINT `FK2mo44agfwqm8c3yakv0u6hevc` FOREIGN KEY (`tickets_id`) REFERENCES `ticket` (`id`),
    CONSTRAINT `FK3mrbqyn3ga9avpqwj4eqfp8v4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;