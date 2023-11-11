package com.hanium.seeku.Controller;

import com.hanium.seeku.Model.PodUsage;
import com.hanium.seeku.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class main {
    private final DaemonsetService daemonService;
    private final NamespaceService namespaceService;
    private final NodeService nodeService;
    private final PodService podService;
    private final ReplicasetService replicaService;
    private final StatefulsetService statefulService;

    @GetMapping
    public String getDashboardItems(Model model){
        int namespaceCnt = namespaceService.getNamespaceList().size();
        int deploymentsCnt = podService.getDeploymentsList().getItems().size();
        int podCnt = podService.getPodList().getItems().size();
        int replicaCnt = replicaService.getReplicaList().getItems().size();
        int statefulCnt = statefulService.getStatefulList().getItems().size();
        int DemonCnt = daemonService.getDemonList().getItems().size();


        System.out.println("DemonCnt = " + DemonCnt);
        System.out.println("statefulCnt = " + statefulCnt);
        System.out.println("deploymentsCnt = " + deploymentsCnt);
        System.out.println("podCnt = " + podCnt);

        model.addAttribute("namespace", namespaceCnt);
        model.addAttribute("deployments", deploymentsCnt);
        model.addAttribute("liveReplicaSet", replicaCnt);
        model.addAttribute("daemonSet", DemonCnt);
        model.addAttribute("statefulSet", statefulCnt);
        model.addAttribute("pod", podCnt);

        model.addAttribute("podUsage", new PodUsage());

        model.addAttribute(
                "podlist", deploymentsCnt
        );
        return "Dashboard";
    }


    @GetMapping("dashboard/workloads/cron")
    public String Cron(){
        return "dashboard/workloads/Cron";
    }

    @GetMapping("dashboard/workloads/daemon")
    public String Daemon(){
        return "dashboard/workloads/Daemon";
    }

    @GetMapping("dashboard/workloads/pods")
    public String Pods(){
        return "dashboard/workloads/Pods";
    }

    @GetMapping("dashboard/workloads/replica")
    public String Replica(){
        return "dashboard/workloads/Replica";
    }

    @GetMapping("dashboard/workloads/stateful")
    public String Stateful(){
        return "dashboard/workloads/Stateful";
    }

    @GetMapping("alert/db")
    public String DB(){
        return "alert/DB";
    }

    @GetMapping("alert/exclusion")
    public String Exclusion(){
        return "alert/Exclusion";
    }

    @GetMapping("alert/identical")
    public String Identical(){
        return "alert/Identical";
    }

    @GetMapping("alert/notification")
    public String Alert_notification(){
        return "alert/Alert";
    }
}
