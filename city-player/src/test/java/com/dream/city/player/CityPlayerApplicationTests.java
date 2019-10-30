package com.dream.city.player;

import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.base.model.mapper.RelationTreeMapper;
import com.dream.city.player.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityPlayerApplicationTests {
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    RelationTreeMapper treeMapper;
    @Autowired
    PlayerAccountMapper accountMapper;

    @Test
    public void contextLoads() {
    }


    @Test
    public void generatePlayers() {
        final int num = 0;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10 - i; j++) {
                Player player = new Player();
                player.setPlayerTradePass("");
                player.setPlayerName("18" + i + "1234567" + j);
                player.setPlayerId(UUID.randomUUID().toString().replace("-",""));
                player.setPlayerNick("18" + i + "12345678" + j);
                player.setCreateTime(new Date());
                player.setPlayerInvite("18" + i + "12" + j);
                player.setIsValid(1);
                player.setPlayerPass("dc483e80a7a0bd9ef71d8cf973673924");
                player.setId(0l);

                playerMapper.insertSelective(player);

                Player parent = playerMapper.getPlayerByAccount("18" + i + "12345671");
                RelationTree parentTree= null;
                if (i>1){
                    parentTree = treeMapper.getTreeByPlayerId("18" + (i-1) + "1234567" + j);
                }else{
                    parentTree = treeMapper.getTreeByPlayerId(parent.getPlayerId());
                }

                RelationTree tree = new RelationTree();
                tree.setTreeId(0);
                tree.setCreateTime(new Timestamp(System.currentTimeMillis()));
                if (i == 1 && j == 1) {
                    tree.setTreePlayerId(player.getPlayerId());
                    tree.setTreeParentId("system");
                    tree.setTreeChilds(10);
                    tree.setTreeGrandson(55);
                    tree.setTreeRelation("system/" + "18" + i + "12" + j);
                } else {
                    tree.setTreeParentId(parent.getPlayerId());
                    tree.setTreePlayerId(player.getPlayerId());
                    tree.setTreeChilds((10 - i));
                    tree.setTreeGrandson(i * (10 - i));
                    tree.setTreeRelation(parentTree.getTreeRelation() + "/" + "18" + i + "12" + j);
                    tree.setTreeId(0);
                    tree.setSendAuto("0");
                }

                switch (tree.getTreeGrandson() + tree.getTreeChilds()) {
                    case 3:
                        tree.setTreeLevel(1);
                        break;
                    case 9:
                        tree.setTreeLevel(2);
                        break;
                    case 27:
                        tree.setTreeLevel(3);
                        break;
                    case 81:
                        tree.setTreeLevel(4);
                        break;
                    default:
                        tree.setTreeLevel(0);
                        break;
                }

                treeMapper.insert(tree);

                PlayerAccount playerAccount = new PlayerAccount();
                BigDecimal amont = new BigDecimal(1000);
                BigDecimal amont1 = new BigDecimal(100);
                playerAccount.setAccUsdtAvailable(amont);
                playerAccount.setAccUsdt(amont);

                playerAccount.setAccMt(amont1);
                playerAccount.setAccMtAvailable(amont1);

                playerAccount.setAccPlayerId(player.getPlayerId());
                playerAccount.setAccId(0);
                playerAccount.setAccAddr(UUID.randomUUID().toString().replace("-",""));
                playerAccount.setCreateTime(new Date());

                accountMapper.insertAccount(playerAccount);

            }
        }
    }

}
