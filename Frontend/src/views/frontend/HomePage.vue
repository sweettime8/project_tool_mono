<template>
  <Layout>
    <div class="row">
      <div class="col-xl-16">
                    <div>
                      <div class="text-center">
                        <div class="col-16">
                          <div>
                            <h5 class="mb-0">PROGRAMING</h5>
                          </div>
                        </div>
                      </div>
                      <!-- end row -->

                      <hr class="mb-4" />

                      <div class="row">
                        <div class="col-sm-4">
                          <div class="card p-1 border shadow-none">
                            <div class="p-3">
                              <h5>
                                <router-link to="/blog/detail" class="text-dark"
                                  >Java</router-link
                                >
                              </h5>
                              <p class="text-muted mb-0">10 Apr, 2020</p>
                            </div>

                            <div class="position-relative">
                              <img
                                src="@/assets/images/small/img-2.jpg"
                                alt=""
                                class="img-thumbnail"
                              />
                            </div>

                            <div class="p-3">
                              <ul class="list-inline">
                                <li class="list-inline-item me-3">
                                  <a href="javascript:void(0);" class="text-muted">
                                    <i
                                      class="bx bx-purchase-tag-alt align-middle text-muted me-1"
                                    ></i>
                                    Project
                                  </a>
                                </li>
                                <li class="list-inline-item me-3">
                                  <a href="javascript:void(0);" class="text-muted">
                                    <i
                                      class="bx bx-comment-dots align-middle text-muted me-1"
                                    ></i>
                                    12 Comments
                                  </a>
                                </li>
                              </ul>
                              <p>
                                Neque porro quisquam est, qui dolorem ipsum quia
                                dolor sit amet
                              </p>

                              <div>
                                <a href="javascript:void(0);" class="text-primary"
                                  >Read more <i class="mdi mdi-arrow-right"></i
                                ></a>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div class="col-sm-4">
                          <div class="card p-1 border shadow-none">
                            <div class="p-3">
                              <h5>
                                <router-link to="/blog/detail" class="text-dark"
                                  >VueJs</router-link
                                >
                              </h5>
                              <p class="text-muted mb-0">24 Mar, 2020</p>
                            </div>

                            <div class="position-relative">
                              <img
                                src="@/assets/images/small/img-6.jpg"
                                alt=""
                                class="img-thumbnail"
                              />

                              <div class="blog-play-icon">
                                <a
                                  href="javascript: void(0);"
                                  class="avatar-sm d-block mx-auto"
                                >
                                  <span
                                    class="avatar-title rounded-circle font-size-18"
                                    ><i class="mdi mdi-play"></i
                                  ></span>
                                </a>
                              </div>
                            </div>
                            <div class="p-3">
                              <ul class="list-inline">
                                <li class="list-inline-item me-3">
                                  <a href="javascript:void(0);" class="text-muted">
                                    <i
                                      class="bx bx-purchase-tag-alt align-middle text-muted me-1"
                                    ></i>
                                    Development
                                  </a>
                                </li>
                                <li class="list-inline-item me-3">
                                  <a href="javascript:void(0);" class="text-muted">
                                    <i
                                      class="bx bx-comment-dots align-middle text-muted me-1"
                                    ></i>
                                    08 Comments
                                  </a>
                                </li>
                              </ul>

                              <p>
                                At vero eos et accusamus et iusto odio
                                dignissimos ducimus quos
                              </p>

                              <div>
                                <a href="javascript:void(0);" class="text-primary"
                                  >Read more <i class="mdi mdi-arrow-right"></i
                                ></a>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div class="col-sm-4">
                          <div class="card p-1 border shadow-none">
                            <div class="p-3">
                              <h5>
                                <router-link to="/blog/detail" class="text-dark"
                                  >Database</router-link
                                >
                              </h5>
                              <p class="text-muted mb-0">24 Mar, 2020</p>
                            </div>

                            <div class="position-relative">
                              <img
                                src="@/assets/images/small/img-6.jpg"
                                alt=""
                                class="img-thumbnail"
                              />

                              <div class="blog-play-icon">
                                <a
                                  href="javascript: void(0);"
                                  class="avatar-sm d-block mx-auto"
                                >
                                  <span
                                    class="avatar-title rounded-circle font-size-18"
                                    ><i class="mdi mdi-play"></i
                                  ></span>
                                </a>
                              </div>
                            </div>
                            <div class="p-3">
                              <ul class="list-inline">
                                <li class="list-inline-item me-3">
                                  <a href="javascript:void(0);" class="text-muted">
                                    <i
                                      class="bx bx-purchase-tag-alt align-middle text-muted me-1"
                                    ></i>
                                    Development
                                  </a>
                                </li>
                                <li class="list-inline-item me-3">
                                  <a href="javascript:void(0);" class="text-muted">
                                    <i
                                      class="bx bx-comment-dots align-middle text-muted me-1"
                                    ></i>
                                    08 Comments
                                  </a>
                                </li>
                              </ul>

                              <p>
                                At vero eos et accusamus et iusto odio
                                dignissimos ducimus quos
                              </p>

                              <div>
                                <a href="javascript:void(0);" class="text-primary"
                                  >Read more <i class="mdi mdi-arrow-right"></i
                                ></a>
                              </div>
                            </div>
                          </div>
                        </div>                        
                      </div>

                    </div>
                  </div>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/layouts/main";
import appConfig from "@/app.config";
import { layoutMethods, layoutComputed } from "@/state/helpers";
/**
 * Dashboard Component
 */
export default {
  page: {
    title: "Dashboard",
    meta: [
      {
        name: "description",
        content: appConfig.description,
      },
    ],
  },
  components: {
    Layout,
  },
  data() {
    return {
      title: "Dashboard",
      items: [
        {
          text: "Dashboards",
          href: "/",
        },
        {
          text: "Default",
          active: true,
        },
      ],
      statData: [
        {
          icon: "bx bx-copy-alt",
          title: "Orders",
          value: "1,235",
        },
        {
          icon: "bx bx-archive-in",
          title: "Revenue",
          value: "$35, 723",
        },
        {
          icon: "bx bx-purchase-tag-alt",
          title: "Average Price",
          value: "$16.2",
        },
      ],
      transactions: [
        {
          id: "#SK2540",
          name: "Neal Matthews",
          date: "07 Oct, 2019",
          total: "$400",
          status: "Paid",
          payment: ["fa-cc-mastercard", "Mastercard"],
          index: 1,
        },
        {
          id: "#SK2541",
          name: "Jamal Burnett",
          date: "07 Oct, 2019",
          total: "$380",
          status: "Chargeback",
          payment: ["fa-cc-visa", "Visa"],
          index: 2,
        },
        {
          id: "#SK2542",
          name: "Juan Mitchell",
          date: "06 Oct, 2019",
          total: "$384",
          status: "Paid",
          payment: ["fab fa-cc-paypal", "Paypal"],
          index: 3,
        },
        {
          id: "#SK2543",
          name: "Barry Dick",
          date: "05 Oct, 2019",
          total: "$412",
          status: "Paid",
          payment: ["fa-cc-mastercard", "Mastercard"],
          index: 4,
        },
        {
          id: "#SK2544",
          name: "Ronald Taylor",
          date: "04 Oct, 2019",
          total: "$404",
          status: "Refund",
          payment: ["fa-cc-visa", "Visa"],
          index: 5,
        },
        {
          id: "#SK2545",
          name: "Jacob Hunter",
          date: "04 Oct, 2019",
          total: "$392",
          status: "Paid",
          payment: ["fab fa-cc-paypal", "Paypal"],
          index: 6,
        },
      ],
      showModal: false,
      isLoading: false,
      fullPage: true,
      canCancel: false,
      useSlot: false,
      loader: "spinner",
      color: "#007bff",
      bgColor: "#ffffff",
      height: 128,
      width: 128,
      timeout: 3000, //ms
      fetchingStats: true,
      earningStatus: true,
    };
  },
  created() {
      this.changeLayout();
  },
  methods :{
      ...layoutMethods,
    async changeLayout(){
      this.changeLayoutType({
        layoutType: 'horizontal',
      }); 
    }
  },
  mounted() {
    setTimeout(() => {
      this.showModal = true;
    }, 1500);
  },
};
</script>
