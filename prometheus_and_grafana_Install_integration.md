# 📡 Connect Local Prometheus to Grafana Cloud

This guide explains how to configure your local Prometheus instance to push metrics to Grafana Cloud using `remote_write`. Once connected, you can visualize and monitor your Spring Boot microservices in Grafana dashboards.

---

## 🚀 Prerequisites

- A running **Prometheus** instance on your local machine
- A **Grafana Cloud** account ([Sign up here](https://grafana.com/products/cloud/))
- Spring Boot app exposing metrics via Micrometer (optional but recommended)

---

## 🔧 Step 1: Get Grafana Cloud Remote Write Credentials

1. Log in to [Grafana Cloud Portal](https://grafana.com/cloud/)
2. Navigate to:  
   **Connections → Prometheus → Send Metrics**
3. Copy the following:
    - `remote_write` URL
    - **Username**
    - **API Key** (click "Generate now" if needed)

---

## ⚙️ Step 2: Update Prometheus Configuration

Edit your `prometheus.yml` file:

```yaml
global:
  scrape_interval: 15s
  evaluation_interval: 15s

remote_write:
  - url: https://<your-remote-write-url>
    basic_auth:
      username: <your-username>
      password: <your-api-key>

scrape_configs:
  - job_name: "spring-boot-app"
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["localhost:8080"]
        labels:
          app: "spring-boot"


# 📡 Connect Local Prometheus to Grafana Cloud

This guide explains how to install Prometheus locally, configure it to scrape metrics from your Spring Boot app, and push those metrics to Grafana Cloud using `remote_write`.

---

## 🛠️ Step 0: Install and Run Prometheus Locally

### ✅ 1. Download Prometheus

Visit the [Prometheus downloads page](https://prometheus.io/download/) and download the latest version for your OS.

#### For Linux/macOS:
```bash
wget https://github.com/prometheus/prometheus/releases/latest/download/prometheus-*.linux-amd64.tar.gz
tar -xvf prometheus-*.linux-amd64.tar.gz
cd prometheus-*.linux-amd64
